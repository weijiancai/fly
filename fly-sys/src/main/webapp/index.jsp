<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>FLY-SYS系统</title>
    <SCRIPT src="http://java.com/js/dtjava.js"></SCRIPT>

    <script>
        function javafxEmbed_ensemble() {
            dtjava.embed(
                    {
                        id : 'ensemble',
                        url : 'fxsys.jnlp',
                        placeholder : 'javafx-app-placeholder',
                        width : '100%',
                        height : '100%'
                    },
                    {
                        javafx : '2.0+'
                    },
                    {}
            );
        }
        <!-- Embed FX application into web page once page is loaded -->
        dtjava.addOnloadCallback(javafxEmbed_ensemble);
    </script>

    <style type="text/css">
        html, body, #javafx-app-placeholder, #ensemble-app {
            margin: 0;
            overflow: hidden;
            padding: 0;
            width: 100%;
            height: 100%;
        }
    </style>
    <script>
        function hashchanged(event) {
            var applet = document.getElementById('ensemble');
            if (applet != null) {
                try {
                    applet.hashChanged(location.hash);
                } catch (err) {
                    console.log("JS Exception when trying to pass hash to Ensmeble Applet: " + err);
                }
            }
        }
    </script>
</head>
<body onhashchange="hashchanged(event)"><div id='javafx-app-placeholder'></div></body>
</html>