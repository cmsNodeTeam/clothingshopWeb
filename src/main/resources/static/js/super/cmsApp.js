/**
 * Created by CC on 2017/12/30.
 */
"use strict";
var app = angular.module('appCMS', ['ngRoute', 'cmsServices', 'cmsControllers']);

//angular路由控制
app.config(function ($routeProvider) {
    $routeProvider.when('/home', {
        //主页
        templateUrl: '/web/home',
        controller: 'commonCtrl'
    }).when('/test', {
        //测试页面
        templateUrl: '/web/test',
        controller: 'commonCtrl'
    }).otherwise({
        redirectTo: '/home'
    });
});

//清除模板缓存
app.run(function ($rootScope, $templateCache, $browser, $location, navLinkService) {
    $rootScope.$on('$routeChangeStart', function (event, next, current) {
        if (typeof(current) !== 'undefined') {
            $templateCache.remove(current['templateUrl']);
        }
    });

    //路由改变成功时触发
    $rootScope.$on('$locationChangeSuccess', function (event, next, current) {
        // $.setLocalStore('currentUrl', $location.path().substr(1));
        // navLinkService.init();
        // if (next !== current) {
        //     var domain = location.origin + $browser.baseHref();//域名
        //     $.setLocalStore('hashUrl', current.replace(domain, ''));
        //     navLinkService.refresh();
        // }
    });
    //当路由成功后才触发controller
});

app.config(function ($httpProvider, $locationProvider) {
    $locationProvider.hashPrefix('');
    $httpProvider.interceptors.push('RequestHandler');
});
