/**
 * Created by CC on 2017/12/30.
 */
"use strict";
var cmsControllers = angular.module('cmsControllers', []);

cmsControllers.controller('commonCtrl', function (cmsConfig) {
    cmsConfig.commonCtrlFn();
    $('.box').each(function () {
        $(this).boxWidget();
    });
});

cmsControllers.controller('navBarController', function ($scope, superService, cmsConfig) {
    //切换语言
    $scope.toggleLang = superService.toggleLang;
    //用户退出
    $scope.logOut = superService.logOut;
    //当切换侧边栏时记住选择
    $scope.changeNavbar = function () {
        if ($("body").hasClass('sidebar-collapse')) {
            $.setLocalStore('sidebar-collapse', false);
        } else {
            $.setLocalStore('sidebar-collapse', true);
        }
    };
    $scope.langProp = cmsConfig.langProp;
    $scope.langType = $.getLangType();
    $scope.langLogo = cmsConfig.langProp[$scope.langType];
});