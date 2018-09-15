/**
 * Created by CC on 2016/5/30.
 */
"use strict";
+(function ($) {

    $.initSize = {
        resizeBody: function () {

            //方式2
            var height = $('nav').height();
            var minHeight = $(window).height() - height;
            $('#ngView').css({
                // 'margin-top': height + 'px',
                'min-height': minHeight + 'px'
                // 'padding-bottom': '40px'
            });
        },
        resizeModal: function () {
            //改变模态框的高度
            var modal = $('.modal-body');
            //padding-top和bottom各30px,title 56px,footer 65px
            var maxHeight = $(window).height() - 30 * 2 - 56 - 65;
            modal.css({
                "max-height": maxHeight + 'px',
                overflow: 'auto'
            });
        }
    };

    if($.getLocalStore('sidebar-collapse') === 'true'){
        $('body').addClass('sidebar-collapse');
    }

    $(window).resize(function () {
        //当浏览器大小变化时
        $.initSize.resizeModal();
        $.initSize.resizeBody();
    });

    $('body').tooltip({
        selector: "[data-toggle='tooltip']",
        container: 'body'
    });

})(jQuery);
