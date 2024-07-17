$(function(){


})

/**
 * 에러 팝업
 */
$.fn.errorAlert = function(errorJson, callBackFunction) {
    $.alert({
        theme : "material",
        type: 'red',
        useBootstrap : false,
        icon: "fa fa-warning",
        title: "에러발생",
        escapeKey: "close",
        animation: 'none',
        content	: "<span style='font-size:15px;'>["+errorJson.status+"]</span>" +
                    "<br><br> <span style='font-size:15px;'>"+errorJson.message+"</span>",
        boxWidth: "400px",
        buttons	: {
            close : {
                text : "확인",
                btnClass : "btn-dark"
            }
        },
        onClose : function(){
            if (typeof callBackFunction == "function") {
                return callBackFunction();
            } else {
                return callBackFunction;
            }
        }
    });
}

/**
 * ajax 에러 공통 처리
 * */
$.ajaxSetup({
    error: function (xhr, status, err) {
        if (xhr.status === 401) {
            window.location.replace("/login");
        } else {
            $.fn.errorAlert(xhr.responseJSON);
        }
    },
});