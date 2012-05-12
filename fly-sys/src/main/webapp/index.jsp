<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>FLY-SYS系统</title>
    <SCRIPT src="http://java.com/js/dtjava.js"></SCRIPT>

    <script>
        function javafxEmbed() {
            dtjava.embed(
                    {
                        id : 'ensemble',
                        url : './fxclient/fly-fxsys.jnlp',
                        placeholder : 'javafx-app-placeholder',
                        width : '100%',
                        height : '100%',
                        jnlp_content : 'PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0idXRmLTgiPz4NCjxqbmxwIHNwZWM9IjEuMCIgeG1sbnM6amZ4PSJodHRwOi8vamF2YWZ4LmNvbSIgaHJlZj0iZmx5LWZ4c3lzLmpubHAiPg0KICA8aW5mb3JtYXRpb24+DQogICAgPHRpdGxlPmZseS1meHN5czwvdGl0bGU+DQogICAgPHZlbmRvcj53ZWlqaWFuY2FpPC92ZW5kb3I+DQogICAgPGRlc2NyaXB0aW9uPlNhbXBsZSBKYXZhRlggMi4wIGFwcGxpY2F0aW9uLjwvZGVzY3JpcHRpb24+DQogICAgPG9mZmxpbmUtYWxsb3dlZC8+DQogIDwvaW5mb3JtYXRpb24+DQogIDxyZXNvdXJjZXMgb3M9IldpbmRvd3MiPg0KICAgIDxqZng6amF2YWZ4LXJ1bnRpbWUgdmVyc2lvbj0iMi4wKyIgaHJlZj0iaHR0cDovL2phdmFkbC5zdW4uY29tL3dlYmFwcHMvZG93bmxvYWQvR2V0RmlsZS9qYXZhZngtbGF0ZXN0L3dpbmRvd3MtaTU4Ni9qYXZhZngyLmpubHAiLz4NCiAgPC9yZXNvdXJjZXM+DQogIDxyZXNvdXJjZXM+DQogICAgPGoyc2UgdmVyc2lvbj0iMS42KyIgaHJlZj0iaHR0cDovL2phdmEuc3VuLmNvbS9wcm9kdWN0cy9hdXRvZGwvajJzZSIvPg0KICAgIDxqYXIgaHJlZj0iZmx5LWZ4c3lzLmphciIgc2l6ZT0iMTU1NTAiIGRvd25sb2FkPSJlYWdlciIgLz4NCiAgPC9yZXNvdXJjZXM+DQo8c2VjdXJpdHk+DQogIDxhbGwtcGVybWlzc2lvbnMvPg0KPC9zZWN1cml0eT4NCiAgPHNob3J0Y3V0PjxkZXNrdG9wLz48L3Nob3J0Y3V0Pg0KICA8YXBwbGV0LWRlc2MgIHdpZHRoPSI4MDAiIGhlaWdodD0iNjAwIiBtYWluLWNsYXNzPSJjb20uamF2YWZ4Lm1haW4uTm9KYXZhRlhGYWxsYmFjayIgIG5hbWU9ImZseS1meHN5cyIgLz4NCiAgPGpmeDpqYXZhZngtZGVzYyAgd2lkdGg9IjgwMCIgaGVpZ2h0PSI2MDAiIG1haW4tY2xhc3M9ImNvbS5mbHkuZnhzeXMuRnhTeXMiICBuYW1lPSJmbHktZnhzeXMiIC8+DQogIDx1cGRhdGUgY2hlY2s9ImJhY2tncm91bmQiLz4NCjwvam5scD4NCg=='
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