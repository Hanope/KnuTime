jQuery(document).ready(function($) {
    var transitionEnd = 'webkitTransitionEnd otransitionend oTransitionEnd msTransitionEnd transitionend';
    var transitionsSupported = ($('.csstransitions').length > 0);
    //if browser does not support transitions - use a different event to trigger them
    if (!transitionsSupported) transitionEnd = 'noTransition';

    var timelineItems, timelineStart, timelineUnitDuration;
    var eventSlotHeight;




    //should add a loding while the events are organized 

    function SchedulePlan(element) {
        this.element = element;
        this.timeline = this.element.find('.timeline');
        this.timelineItems = this.timeline.find('li');
        this.timelineItemsNumber = this.timelineItems.length;
        this.timelineStart = getScheduleTimestamp(this.timelineItems.eq(0).text());
        //need to store delta (in our case half hour) timestamp
        this.timelineUnitDuration = getScheduleTimestamp(this.timelineItems.eq(1).text()) - getScheduleTimestamp(this.timelineItems.eq(0).text());

        this.eventsWrapper = this.element.find('.events');
        this.eventsGroup = this.eventsWrapper.find('.events-group');
        this.singleEvents = this.eventsGroup.find('.single-event');
        this.eventSlotHeight = this.eventsGroup.eq(0).children('.top-info').outerHeight();

        this.modal = this.element.find('.event-modal');
        this.modalHeader = this.modal.find('.header');
        this.modalHeaderBg = this.modal.find('.header-bg');
        this.modalBody = this.modal.find('.body');
        this.modalBodyBg = this.modal.find('.body-bg');
        this.modalMaxWidth = 800;
        this.modalMaxHeight = 480;

        this.animating = false;


        timelineItems = this.timelineItems;
        timelineStart = this.timelineStart;
        timelineUnitDuration = this.timelineUnitDuration;
        eventSlotHeight = this.eventSlotHeight;

        this.initSchedule();
    }

    SchedulePlan.prototype.initSchedule = function() {
        this.scheduleReset();
        this.initEvents();
    };

    SchedulePlan.prototype.scheduleReset = function() {
        var mq = this.mq();
        if (mq == 'desktop' && !this.element.hasClass('js-full')) {
            //in this case you are on a desktop version (first load or resize from mobile)
            this.eventSlotHeight = this.eventsGroup.eq(0).children('.top-info').outerHeight();
            this.element.addClass('js-full');
            this.placeEvents();
            this.element.hasClass('modal-is-open') && this.checkEventModal();
        } else if (mq == 'mobile' && this.element.hasClass('js-full')) {
            //in this case you are on a mobile version (first load or resize from desktop)
            this.element.removeClass('js-full loading');
            this.eventsGroup.children('ul').add(this.singleEvents).removeAttr('style');
            this.eventsWrapper.children('.grid-line').remove();
            this.element.hasClass('modal-is-open') && this.checkEventModal();
        } else if (mq == 'desktop' && this.element.hasClass('modal-is-open')) {
            //on a mobile version with modal open - need to resize/move modal window
            this.checkEventModal('desktop');
            this.element.removeClass('loading');
        } else {
            this.element.removeClass('loading');
        }
    };

    SchedulePlan.prototype.initEvents = function() {
        var self = this;

        this.singleEvents.each(function() {
            //create the .event-date element for each event
            var durationLabel = '<span class="event-date">' + $(this).data('start') + ' - ' + $(this).data('end') + '</span>';
            $(this).children('a').prepend($(durationLabel));

            //detect click on the event and open the modal
            $(this).on('click', 'a', function(event) {
                event.preventDefault();
                if (!self.animating) self.openModal($(this));
            });
        });

        //close modal window
        this.modal.on('click', '.close', function(event) {
            event.preventDefault();
            if (!self.animating) self.closeModal(self.eventsGroup.find('.selected-event'));
        });
        this.element.on('click', '.cover-layer', function(event) {
            if (!self.animating && self.element.hasClass('modal-is-open')) self.closeModal(self.eventsGroup.find('.selected-event'));
        });
    };

    SchedulePlan.prototype.placeEvents = function() {
        var self = this;
        this.singleEvents.each(function() {
            //place each event in the grid -> need to set top position and height
            var start = getScheduleTimestamp($(this).attr('data-start')),
                duration = getScheduleTimestamp($(this).attr('data-end')) - start;

            var eventTop = self.eventSlotHeight * (start - self.timelineStart) / self.timelineUnitDuration,
                eventHeight = self.eventSlotHeight * duration / self.timelineUnitDuration;

            $(this).css({
                top: (eventTop - 1) + 'px',
                height: (eventHeight + 1) + 'px'
            });
        });

        this.element.removeClass('loading');
    };

    SchedulePlan.prototype.openModal = function(event) {
        var self = this;
        var mq = self.mq();
        this.animating = true;

        //update event name and time
        this.modalHeader.find('.class-name').text(event.find('.class-name').text());
        this.modalHeader.find('.event-date').text(event.find('.event-date').text());
        this.modalHeader.find('.professor-name').text(event.find('.professor-name').text());
        this.modalHeader.find('.class-room').text(event.find('.class-room').text());
        this.modal.attr('data-event', event.parent().attr('data-event'));

        //update event content
        this.modalBody.find('.event-info').load(event.parent().attr('data-content') + '.html .event-info > *', function(data) {
            //once the event content has been loaded
            self.element.addClass('content-loaded');
        });

        this.element.addClass('modal-is-open');

        setTimeout(function() {
            //fixes a flash when an event is selected - desktop version only
            event.parent('li').addClass('selected-event');
        }, 10);

        if (mq == 'mobile') {
            self.modal.one(transitionEnd, function() {
                self.modal.off(transitionEnd);
                self.animating = false;
            });
        } else {
            var eventTop = event.offset().top - $(window).scrollTop(),
                eventLeft = event.offset().left,
                eventHeight = event.innerHeight(),
                eventWidth = event.innerWidth();

            var windowWidth = $(window).width(),
                windowHeight = $(window).height();

            var modalWidth = (windowWidth * .8 > self.modalMaxWidth) ? self.modalMaxWidth : windowWidth * .8,
                modalHeight = (windowHeight * .8 > self.modalMaxHeight) ? self.modalMaxHeight : windowHeight * .8;

            var modalTranslateX = parseInt((windowWidth - modalWidth) / 2 - eventLeft),
                modalTranslateY = parseInt((windowHeight - modalHeight) / 2 - eventTop);

            var HeaderBgScaleY = modalHeight / eventHeight,
                BodyBgScaleX = (modalWidth - eventWidth);

            //change modal height/width and translate it
            self.modal.css({
                top: eventTop + 'px',
                left: eventLeft + 'px',
                height: modalHeight + 'px',
                width: modalWidth + 'px',
            });
            transformElement(self.modal, 'translateY(' + modalTranslateY + 'px) translateX(' + modalTranslateX + 'px)');

            //set modalHeader width
            self.modalHeader.css({
                width: eventWidth + 'px',
            });
            //set modalBody left margin
            self.modalBody.css({
                marginLeft: eventWidth + 'px',
            });

            //change modalBodyBg height/width ans scale it
            self.modalBodyBg.css({
                height: eventHeight + 'px',
                width: '1px',
            });
            transformElement(self.modalBodyBg, 'scaleY(' + HeaderBgScaleY + ') scaleX(' + BodyBgScaleX + ')');

            //change modal modalHeaderBg height/width and scale it
            self.modalHeaderBg.css({
                height: eventHeight + 'px',
                width: eventWidth + 'px',
            });
            transformElement(self.modalHeaderBg, 'scaleY(' + HeaderBgScaleY + ')');

            self.modalHeaderBg.one(transitionEnd, function() {
                //wait for the  end of the modalHeaderBg transformation and show the modal content
                self.modalHeaderBg.off(transitionEnd);
                self.animating = false;
                self.element.addClass('animation-completed');
            });
        }

        //if browser do not support transitions -> no need to wait for the end of it
        if (!transitionsSupported) self.modal.add(self.modalHeaderBg).trigger(transitionEnd);
    };

    SchedulePlan.prototype.closeModal = function(event) {
        var self = this;
        var mq = self.mq();

        this.animating = true;

        if (mq == 'mobile') {
            this.element.removeClass('modal-is-open');
            this.modal.one(transitionEnd, function() {
                self.modal.off(transitionEnd);
                self.animating = false;
                self.element.removeClass('content-loaded');
                event.removeClass('selected-event');
            });
        } else {
            var eventTop = event.offset().top - $(window).scrollTop(),
                eventLeft = event.offset().left,
                eventHeight = event.innerHeight(),
                eventWidth = event.innerWidth();

            var modalTop = Number(self.modal.css('top').replace('px', '')),
                modalLeft = Number(self.modal.css('left').replace('px', ''));

            var modalTranslateX = eventLeft - modalLeft,
                modalTranslateY = eventTop - modalTop;

            self.element.removeClass('animation-completed modal-is-open');

            //change modal width/height and translate it
            this.modal.css({
                width: eventWidth + 'px',
                height: eventHeight + 'px'
            });
            transformElement(self.modal, 'translateX(' + modalTranslateX + 'px) translateY(' + modalTranslateY + 'px)');

            //scale down modalBodyBg element
            transformElement(self.modalBodyBg, 'scaleX(0) scaleY(1)');
            //scale down modalHeaderBg element
            transformElement(self.modalHeaderBg, 'scaleY(1)');

            this.modalHeaderBg.one(transitionEnd, function() {
                //wait for the  end of the modalHeaderBg transformation and reset modal style
                self.modalHeaderBg.off(transitionEnd);
                self.modal.addClass('no-transition');
                setTimeout(function() {
                    self.modal.add(self.modalHeader).add(self.modalBody).add(self.modalHeaderBg).add(self.modalBodyBg).attr('style', '');
                }, 10);
                setTimeout(function() {
                    self.modal.removeClass('no-transition');
                }, 20);

                self.animating = false;
                self.element.removeClass('content-loaded');
                event.removeClass('selected-event');
            });
        }

        //browser do not support transitions -> no need to wait for the end of it
        if (!transitionsSupported) self.modal.add(self.modalHeaderBg).trigger(transitionEnd);
    }

    SchedulePlan.prototype.mq = function() {
        //get MQ value ('desktop' or 'mobile') 
        var self = this;
        return window.getComputedStyle(this.element.get(0), '::before').getPropertyValue('content').replace(/["']/g, '');
    };

    SchedulePlan.prototype.checkEventModal = function(device) {
        this.animating = true;
        var self = this;
        var mq = this.mq();

        if (mq == 'mobile') {
            //reset modal style on mobile
            self.modal.add(self.modalHeader).add(self.modalHeaderBg).add(self.modalBody).add(self.modalBodyBg).attr('style', '');
            self.modal.removeClass('no-transition');
            self.animating = false;
        } else if (mq == 'desktop' && self.element.hasClass('modal-is-open')) {
            self.modal.addClass('no-transition');
            self.element.addClass('animation-completed');
            var event = self.eventsGroup.find('.selected-event');

            var eventTop = event.offset().top - $(window).scrollTop(),
                eventLeft = event.offset().left,
                eventHeight = event.innerHeight(),
                eventWidth = event.innerWidth();

            var windowWidth = $(window).width(),
                windowHeight = $(window).height();

            var modalWidth = (windowWidth * .8 > self.modalMaxWidth) ? self.modalMaxWidth : windowWidth * .8,
                modalHeight = (windowHeight * .8 > self.modalMaxHeight) ? self.modalMaxHeight : windowHeight * .8;

            var HeaderBgScaleY = modalHeight / eventHeight,
                BodyBgScaleX = (modalWidth - eventWidth);

            setTimeout(function() {
                self.modal.css({
                    width: modalWidth + 'px',
                    height: modalHeight + 'px',
                    top: (windowHeight / 2 - modalHeight / 2) + 'px',
                    left: (windowWidth / 2 - modalWidth / 2) + 'px',
                });
                transformElement(self.modal, 'translateY(0) translateX(0)');
                //change modal modalBodyBg height/width
                self.modalBodyBg.css({
                    height: modalHeight + 'px',
                    width: '1px',
                });
                transformElement(self.modalBodyBg, 'scaleX(' + BodyBgScaleX + ')');
                //set modalHeader width
                self.modalHeader.css({
                    width: eventWidth + 'px',
                });
                //set modalBody left margin
                self.modalBody.css({
                    marginLeft: eventWidth + 'px',
                });
                //change modal modalHeaderBg height/width and scale it
                self.modalHeaderBg.css({
                    height: eventHeight + 'px',
                    width: eventWidth + 'px',
                });
                transformElement(self.modalHeaderBg, 'scaleY(' + HeaderBgScaleY + ')');
            }, 10);

            setTimeout(function() {
                self.modal.removeClass('no-transition');
                self.animating = false;
            }, 20);
        }
    };

    var schedules = $('.cd-schedule');
    var objSchedulesPlan = [],
        windowResize = false;

    if (schedules.length > 0) {
        schedules.each(function() {
            //create SchedulePlan objects
            objSchedulesPlan.push(new SchedulePlan($(this)));
        });
    }

    $(window).on('resize', function() {
        if (!windowResize) {
            windowResize = true;
            (!window.requestAnimationFrame) ? setTimeout(checkResize): window.requestAnimationFrame(checkResize);
        }
    });

    $(window).keyup(function(event) {
        if (event.keyCode == 27) {
            objSchedulesPlan.forEach(function(element) {
                element.closeModal(element.eventsGroup.find('.selected-event'));
            });
        }
    });

    function checkResize() {
        objSchedulesPlan.forEach(function(element) {
            element.scheduleReset();
        });
        windowResize = false;
    }

    function getScheduleTimestamp(time) {
        //accepts hh:mm format - convert hh:mm to timestamp
        time = time.replace(/ /g, '');
        var timeArray = time.split(':');
        var timeStamp = parseInt(timeArray[0]) * 60 + parseInt(timeArray[1]);
        return timeStamp;
    }

    function transformElement(element, value) {
        element.css({
            '-moz-transform': value,
            '-webkit-transform': value,
            '-ms-transform': value,
            '-o-transform': value,
            'transform': value
        });
    }


    // 과목 검색 이벤트
    $('#course-btn').click(function() {
        $.ajax({
            type: 'GET',
            url: '/course/' + $('#course-text').val(),
            success: function(data) {
                var ul = $('.course-result');

                $.each(data, function(i, course) {
                    ul.append('<li class="list-group-item">' +
                        '<p class="list-course-title course-list">' + course.title + '</p>' +
                        '<p class="list-course-code course-list">' + course.code + '</p>' +
                        '<p class="list-course-instructor course-list">' + course.instructor + '</p>' +
                        '<p class="list-course-location course-list">' + course.location + '</p>' +
                        '<p class="list-course-time course-list">' + course.hours + '</p>' +
                        '</li>');
                });
            }
        });
    });

    $('#course-text').keypress(function(event) {
        if (event.which == 13) {
            $('#course-btn').click();
            return false;
        }
    });

    // 시간표 미리 표시
    $('.course-result').on('mouseenter', 'li', function() {
        var class_info = selected_class_info(this);
        show_class(class_info, 'temp-event');
    });

    $('.course-result').on('mouseleave', 'li', function() {
        $('.temp-event').remove();
    });

    $('.course-result').on('click', 'li', function() {
        var class_info = selected_class_info(this);
        show_class(class_info, '');

    });

    function selected_class_info(element) {
        var days = [];

        var course_time_text = $(element).find('.list-course-time').text();
        var splits = course_time_text.split('/');

        // ['화1A1B2A', '수1A1B2A']
        $.each(splits, function(key, value) {
            var obj = {};
            var times = [];
            var day = '';

            // 화
            var day_text = value.charAt(0);
            // 1A1B2A
            var time_text = value.substring(1);
            // ['1A', '1B', '2A']
            var time_array = time_text.split(/([0-9]{1,2}[A-Z]{1})/);

            $.each(time_array, function(index, time) {
                var start_time, end_time;

                if (time != '') {
                    switch (time) {
                        case '1A': start_time = '09:00'; end_time = '09:30'; break;
                        case '1B': start_time = '09:30'; end_time = '10:00'; break;
                        case '2A': start_time = '10:00'; end_time = '10:30'; break;
                        case '2B': start_time = '10:30'; end_time = '11:00'; break;
                        case '3A': start_time = '11:00'; end_time = '11:30'; break;
                        case '3B': start_time = '11:30'; end_time = '12:00'; break;
                        case '4A': start_time = '12:00'; end_time = '12:30'; break;
                        case '4B': start_time = '12:30'; end_time = '13:00'; break;
                        case '5A': start_time = '13:00'; end_time = '13:30'; break;
                        case '5B': start_time = '13:30'; end_time = '14:00'; break;
                        case '6A': start_time = '14:00'; end_time = '14:30'; break;
                        case '6B': start_time = '14:30'; end_time = '15:00'; break;
                        case '7A': start_time = '15:00'; end_time = '15:30'; break;
                        case '7B': start_time = '15:30'; end_time = '16:00'; break;
                        case '8A': start_time = '16:00'; end_time = '16:30'; break;
                        case '8B': start_time = '16:30'; end_time = '17:00'; break;
                        case '9A': start_time = '17:00'; end_time = '17:30'; break;
                        case '9B': start_time = '17:30'; end_time = '18:00'; break;
                    }

                    var obj = {
                        'start_time': start_time,
                        'end_time': end_time
                    };

                    times.push(obj);
                }
            });

            switch (day_text) {
                case '월': day = 'mon'; break;
                case '화': day = 'tue'; break;
                case '수': day = 'wed'; break;
                case '목': day = 'thu'; break;
                case '금': day = 'fir'; break;
            }

            obj['day'] = day;
            obj['times'] = times;

            days.push(obj);
        });

        var obj = {};
        obj['course_title'] = $(element).find('.list-course-title').text();
        obj['course_instructor'] = $(element).find('.list-course-instructor').text();
        obj['course_location'] = $(element).find('.list-course-location').text();

        obj['times'] = days;

        return obj;
    }


    function show_class(class_info, temp) {
        var course_title = class_info['course_title'];
        var course_instructor = class_info['course_instructor'];
        var course_location = class_info['course_location'];
        var times = class_info['times'];

        $.each(times, function(index, item) {
            var day = item['day'];
            var time = item['times'];
            var course_start_time = time[0]['start_time'];
            var course_end_time = time[time.length - 1]['end_time'];

            var start = getScheduleTimestamp(course_start_time),
                duration = getScheduleTimestamp(course_end_time) - start;

            var eventTop = eventSlotHeight * (start - timelineStart) / timelineUnitDuration,
                eventHeight = eventSlotHeight * duration / timelineUnitDuration;

            var css_top = (eventTop - 1) + 'px',
                css_height = (eventHeight + 1) + 'px';

            var color = 'data-event="event-' + (Math.floor(Math.random() * 12) + 1) + '"';


            var html = '<li class="single-event ' + temp +'" data-start="' + course_start_time + '" data-end="' + course_end_time + '"' + color + ' style="top: ' + css_top + '; height: ' + css_height + '; opacity: 0.8">' +
                '<a href="#"><span class="event-date">' + course_start_time + ' - ' + course_end_time + '</span>' +
                '<em class="class-name">' + course_title + '</em>' +
                '<span class="professor-name">' + course_instructor + '</span>' +
                '<span class="class-room">' + course_location + '</span>' +
                '</a>' +
                '</li>';

            $('#event-' + day).append(html);
        });
    }

});