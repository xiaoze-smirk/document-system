<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/jsps/common/taglibs.jsp"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>欢迎页面</title>
    <style>
        html{font-family:sans-serif;-ms-text-size-adjust:100%;-webkit-text-size-adjust:100%}body{margin:0}article,aside,details,figcaption,figure,footer,header,hgroup,main,nav,section,summary{display:block}audio,canvas,progress,video{display:inline-block;vertical-align:baseline}audio:not([controls]){display:none;height:0}[hidden],template{display:none}a{background:transparent}a:active,a:hover{outline:0}abbr[title]{border-bottom:1px dotted}b,strong{font-weight:bold}dfn{font-style:italic}h1{font-size:2em;margin:0.67em 0}mark{background:#ff0;color:#000}small{font-size:80%}sub,sup{font-size:75%;line-height:0;position:relative;vertical-align:baseline}sup{top:-0.5em}sub{bottom:-0.25em}img{border:0}svg:not(:root){overflow:hidden}figure{margin:1em 40px}hr{-moz-box-sizing:content-box;box-sizing:content-box;height:0}pre{overflow:auto}code,kbd,pre,samp{font-family:monospace, monospace;font-size:1em}button,input,optgroup,select,textarea{color:inherit;font:inherit;margin:0}button{overflow:visible}button,select{text-transform:none}button,html input[type="button"],input[type="reset"],input[type="submit"]{-webkit-appearance:button;cursor:pointer}button[disabled],html input[disabled]{cursor:default}button::-moz-focus-inner,input::-moz-focus-inner{border:0;padding:0}input{line-height:normal}input[type="checkbox"],input[type="radio"]{-moz-box-sizing:border-box;box-sizing:border-box;padding:0}input[type="number"]::-webkit-inner-spin-button,input[type="number"]::-webkit-outer-spin-button{height:auto}input[type="search"]{-webkit-appearance:textfield;-moz-box-sizing:content-box;box-sizing:content-box}input[type="search"]::-webkit-search-cancel-button,input[type="search"]::-webkit-search-decoration{-webkit-appearance:none}fieldset{border:1px solid #c0c0c0;margin:0 2px;padding:0.35em 0.625em 0.75em}legend{border:0;padding:0}textarea{overflow:auto}optgroup{font-weight:bold}table{border-collapse:collapse;border-spacing:0}td,th{padding:0}
        @import url(http://fonts.googleapis.com/css?family=Lato:900);
        *, *:before, *:after{
            box-sizing:border-box;
        }
        body{
            font-family: 'Lato', sans-serif;
            background-image: url("${pageContext.request.contextPath}/images/bg.jpg");
        }
        div.foo{
            width: 90%;
            margin: 0 auto;
            text-align: center;
            line-height: 400px;
        }
        .letter{
            display: inline-block;
            font-weight: 800;
            font-size: 3.5em;
            margin: 3px;
            position: relative;
            color: #00B4F1;
            transform-style: preserve-3d;
            perspective: 400;
            z-index: 1;
        }
        .letter:before, .letter:after{
            position:absolute;
            content: attr(data-letter);
            transform-origin: top left;
            top:0;
            left:0;
        }
        .letter, .letter:before, .letter:after{
            transition: all 0.3s ease-in-out;
        }
        .letter:before{
            color: #fff;
            text-shadow:
                    -1px 0px 1px rgba(255,255,255,.8),
                    1px 0px 1px rgba(0,0,0,.8);
            z-index: 3;
            transform:
                    rotateX(0deg)
                    rotateY(-15deg)
                    rotateZ(0deg);
        }
        .letter:after{
            color: rgba(0,0,0,.11);
            z-index:2;
            transform:
                    scale(1.08,1)
                    rotateX(0deg)
                    rotateY(0deg)
                    rotateZ(0deg)
                    skew(0deg,1deg);
        }
        .letter:hover:before{
            color: #fafafa;
            transform:
                    rotateX(0deg)
                    rotateY(-40deg)
                    rotateZ(0deg);
        }
        .letter:hover:after{
            transform:
                    scale(1.08,1)
                    rotateX(0deg)
                    rotateY(40deg)
                    rotateZ(0deg)
                    skew(0deg,22deg);
        }
    </style>
    <script src="${pageContext.request.contextPath}/js/welcome/prefixfree.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/welcome/modernizr.js"></script>
</head>
<body>
<div class="foo">
    <span class="letter" data-letter="欢">欢</span>
    <span class="letter" data-letter="迎">迎</span>
    <span class="letter" data-letter="使">使</span>
    <span class="letter" data-letter="用">用</span>
    <span class="letter" data-letter="项">项</span>
    <span class="letter" data-letter="目">目</span>
    <span class="letter" data-letter="文">文</span>
    <span class="letter" data-letter="档">档</span>
    <span class="letter" data-letter="管">管</span>
    <span class="letter" data-letter="理">理</span>
    <span class="letter" data-letter="系">系</span>
    <span class="letter" data-letter="统">统</span>
</div>
<script src='${pageContext.request.contextPath}/js/jquery-3.2.1.min.js'></script>
</body>
</html>