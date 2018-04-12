const domain = 'http://my.knu.ac.kr/stpo/stpo/cour/listLectPln/list.action';
const ajax_url = "http://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js";
const findYear = 20171;
const phantom = require('phantom');

var ph;
var page;
var urls = [];

phantom.create().then(function(ph) {
  ph.createPage().then(function(_page) {
    page = _page;
    getUrl(findYear, function(result) {
      urls = result;
      next_page();
    });
  });
});

function handle_page(url) {
  console.log(url);

  page.open(url).then(function(status) {
    page.includeJs(ajax_url).then(function(status_ajax) {
      page.property('content').then(function(content) {
        console.log(content);

        next_page();
      });
    });
  });
}

function next_page() {
  var url = urls.shift();

  if(!url)
    ph.exit();
  else
    handle_page(url);
}

function getUrl(year, callback) {
  var url_arrays = [];
  var mysql = require('mysql');
  var connection = mysql.createConnection({
    host : 'localhost',
    user : 'root',
    password : 'gmlakd123',
    port : 3306,
    database : 'knutime'
  });

  connection.connect();

  connection.query('SELECT * FROM dept_url', function(err, rows, fields) {
    if(err)
      console.log('DB Error');
    else {
      for(var i=0; i<rows.length; i++) {
        var url = domain;
        var param1, param2, param3;
        var depth2_code, depth3_code;

        depth2_code = rows[i]['depth2_code'];
        depth3_code = rows[i]['depth3_code'];
        param1 = rows[i]['parameter1'];
        param2 = rows[i]['parameter2'];
        param3 = rows[i]['parameter3'];

        url += '?' + param2 + '=' + depth2_code;
        url += '&' + param1 + '=' + year;

        if(depth3_code != '')
          url += '&' + param3 + '=' + depth3_code;

        url_arrays.push(url);
      }
    }

    if(typeof callback == 'function') {
      callback(url_arrays);
    }
  });

  connection.end();
}
