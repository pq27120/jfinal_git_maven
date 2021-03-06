define(function (require) {
    var $ = require('jquery');
    require('datepicker')($);
    require('datepickerzh')($);
    require('validate')($);
    require('validatezh')($);

    $(function () {
        $('#datetimepicker').datetimepicker({
            language: 'zh-CN',
            format: 'yyyy-mm-dd hh:ii:ss'
        });

        loadUserSelect();

        $(document).ready(function () {
            setFormValidate();
        });

        function doNext() {
            $('.form-horizontal')[0].submit();
        }

        function setFormValidate() {
            $.validator.setDefaults({
                submitHandler: function () {
                    doNext();
                }
            });

            validator = $(".form-horizontal").validate({
                errorElement: 'span',
                errorClass: 'help-block',
                focusInvalid: false,
                rules: {
                    "pay.amount": {
                        required: true
                    }
                },
                messages: {
                    "pay.amount": {required: "金额不能为空"}
                },
                highlight: function (element) {
                    $(element).closest('.form-group').addClass('has-error');
                },
                success: function (label) {
                    label.closest('.form-group').removeClass('has-error');
                    label.remove();
                },

                errorPlacement: function (error, element) {
                    element.parent('div').append(error);
                },
                alertError: true
            });
        }

    });

    function loadUserSelect() {
        $.ajax({
            type: "GET",
            url: "/user/userList",
            dataType: "json",
            success: function (data) {
                console.info('data.userList.length' + data.userList.length);
                console.info('data.userList' + data.userList);
                if (data != null && data.userList != null) {
                    var userList = data.userList;
                    $.each(userList, function (i, item) {
                        $('#user_id').append($('<option>', {
                            value: item.id,
                            text: item.name
                        }));
                    });
                }
            }
        });
    }
});

