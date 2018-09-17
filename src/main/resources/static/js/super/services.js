/**
 * Created by CC on 2017/12/30.
 */
"use strict";
var cmsServices = angular.module('cmsServices', []);

cmsServices.factory('RequestHandler', function ($q) {
    return {
        'request': function (request) {
            request.headers['X-Requested-With'] = 'XMLHttpRequest';
            if (request.loadingId) request.loadId = $.loadingDiv(request.loadingId);
            return request;
        },
        'response': function (response) {
            var result = response.data;
            if (typeof result === 'string') {
                //这里是因为res.render的时候不知道为什么返回的是字符串而不是json
                //可能是因为单页应用路由请求时返回的参数都是转成字符串的原因
                try {
                    result = JSON.parse(result);
                } catch (e) {
                    if (response.config.loadId)
                        $.cancelLoadingDiv(response.config.loadId);
                    return response;
                }
            }
            if (result.code === -302) {
                window.location.href = result.redirectURL;
            }
            if (response.config.loadId)
                $.cancelLoadingDiv(response.config.loadId);
            return response;
        },
        'responseError': function (rejection) {
            $.cancelLoadingDiv();
            if (rejection.status === 500) {
                $.createAlert('请求服务器500错误');
            }
            return $q.reject(rejection);
        }
    };
});

cmsServices.factory('cmsConfig', function () {
    return {
        commonCtrlFn: function () {
            $.initSize.resizeBody();
            $.initSize.resizeModal();
        },
        langProp: {
            'zh_CN': '简体中文',
            'en_US': 'English'
        }
    };
});

cmsServices.factory('superService', function ($http) {
    return {
        toggleLang: function (type) {
            if (type === $.getLangType())return;
            $http({
                url: 'api/user/change_language',
                method: 'post',
                data:{
                    type: type
                },
                loadingId: 'body'
            }).then(function (response) {
                if (response.data.code === 1) {
                    window.location.reload();
                }
            });
        },
        logOut: function () {
            $http({
                url: 'api/user/logout',
                loadingId: 'body',
                method: 'post'
            });
        }
    }
});

