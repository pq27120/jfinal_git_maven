define(function (require) {
    var $ = require('jquery');
    require('datepicker')($);
    require('datepickerzh')($);


    $('#datetimepicker').datetimepicker({
        language:'zh-CN',
        format: 'yyyy-mm-dd hh:ii:ss'
    });
});

