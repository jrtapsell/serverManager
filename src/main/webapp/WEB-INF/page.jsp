<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <head>
        <title><%= request.getAttribute("page_title")%></title>
        <script src="https://code.getmdl.io/1.3.0/material.min.js"></script>
        <script src="http://wzrd.in/standalone/uuid@latest"></script>
        <script src="https://code.jquery.com/jquery-3.1.1.min.js" integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-blue.min.css" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="icon" type="image/png" href="/favicon.png">
    </head>
</head>
<body>
<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
    <header class="mdl-layout__header">
        <div class="mdl-layout__header-row">
            <!-- Title -->
            <span class="mdl-layout-title">${page_title}</span>
            <!-- Add spacer, to align navigation to the right -->
            <div class="mdl-layout-spacer"></div>
            <!-- Navigation. We hide it in small screens. -->
            <nav class="mdl-navigation">
                <a class="mdl-navigation__link" href="/">Home</a>
                <a class="mdl-navigation__link" href="/servers/">Servers</a>
                <a class="mdl-navigation__link" href="/keys/">Keys</a>
                <a class="mdl-navigation__link" href="/login/">Login</a>
            </nav>
        </div>
    </header>
    <main class="mdl-layout__content">
        <div class="mdl-grid">
        <div class="mdl-layout-spacer"></div>
        <div class="page-content mdl-cell mdl-cell--10-col mdl-grid">
        <c:if test="${not empty page_name}" >
            <jsp:include page='/WEB-INF/${page_name}.jsp'/>
        </c:if>
        </div>
        <div class="mdl-layout-spacer"></div>
        </div>
    </main>
    <div id="demo-toast-example" class="mdl-js-snackbar mdl-snackbar">
        <div class="mdl-snackbar__text"></div>
        <button class="mdl-snackbar__action" type="button"></button>
    </div>
    <style>
        div#demo-toast-example {
            width: 100%;
        }
    </style>
    <script>
        var listener = function(e) {
            console.log("Notification", e);
        };
        function attachWSListener(listener2) {
            worker = new SharedWorker("/shared_worker.js", "Server Manager Update Listener");
            worker.port.addEventListener("message", listener2);
            worker.port.start();
        }
        attachWSListener(listener);
        var snackbarContainer = document.querySelector('#demo-toast-example');
        function toast(message) {
                'use strict';
                var data = {message: message};
                snackbarContainer.MaterialSnackbar.showSnackbar(data);
        }

        function request_delete(url) {
            $.ajax({
                type: 'DELETE',
                url: url == undefined ? "" : url,
                success: function(response) {
                    toast("Deleted successfully");
                },
                error: function () {
                    toast("Delete failed");
                }
            });
        }
    </script>
    <script>
    </script>
</div>
</body>
</html>
