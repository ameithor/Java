<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <!--Enlace a el archivo de estilos CSS alojado en /styles/miestilo.css -->
        <link rel="stylesheet" type="text/css" href="styles/miestilo.css">
        <title>Seleccione Editorial</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script type="text/javascript">
            var xhr;
            var xhr2;
            var lista = new Array();

            function OnLoadAjaxConnect() {
                // solo para Explorer 6 y anteriores
                if (window.ActiveXObject)
                    xhr = new ActiveXObject("Microsoft.XMLHttp");
                // resto de navegadores
                else if ((window.XMLHttpRequest) || (typeof XMLHttpRequest) != undefined)
                    xhr = new XMLHttpRequest();

                // si el navegador no soporta AJAX
                else {
                    alert("Su navegador no soporta AJAX");
                    return;
                }

                OnLoadEnviarPeticion();
            }


            function OnLoadEnviarPeticion() {
                // preparar la peticion
                // true --> peticion asincrona, false --> peticion sincrona
                xhr.open("GET", "ServletEditoriales?", true);

                // informar de la funcion que procesara la respuesta
                xhr.onreadystatechange = OnLoadProcesarRespuesta; // sin parentesis

                // enviar la peticion
                xhr.send(null);
            }

            function GetEditorialEnviarPeticion() {
                
                // Seleccionar el div donde vamos a cargar la lista
                var e = document.getElementById("listahtml");
                
                // Obtenemos el indice de la editorial seleccionada 
                var indexEditorial = e.selectedIndex;

                // preparar la peticion
                // true --> peticion asincrona, false --> peticion sincrona
                // Enviamos el indice como parámetro para poder pasar la consulta 
                // SQL desde el servlet BuscarEditorial.java
                xhr2.open("GET", "BuscarEditorial?editorial=" + indexEditorial, true);

                // informar de la funcion que procesara la respuesta
                xhr2.onreadystatechange = GetEditorialProcesarRespuesta; // sin parentesis

                // enviar la peticion
                xhr2.send(null);
            }



            function OnLoadProcesarRespuesta() {

                // Si 'xhr.readyState == 4' Se ha recibido el cuerpo de la respuesta. Es el momento 
                // en que esta puede procesarse
                if (xhr.readyState == 4) {
                    
                    // Cargamos el xml recibido desd eel servlet en la variable xmlDoc
                    var xmlDoc = xhr.responseXML;
                    var txt = "";
                    
                    // Guardamos todos los valores con etiqueta <nombre> en la lista x
                    var x = xmlDoc.getElementsByTagName("nombre");
                    
                    // Enlazamos la variable listContainer con el <div> listaeditoriales
                    // para cargar las editoriales cargadas desde el servlet
                    var listContainer = document.getElementById("listaeditoriales");
                    var listElement = document.createElement("select");
                    listElement.id = "listahtml";
                    listContainer.appendChild(listElement);

                    // El siguiente bucle for va añadiendo opciones en el select 'listElement'
                    for (i = 0; i < x.length; i++)
                    {
                        var listItem = document.createElement("option");
                        txt = x[i].childNodes[0].nodeValue;
                        listItem.innerHTML = txt;
                        listElement.appendChild(listItem);

                    }

                }

            }

            function GetEditorialProcesarRespuesta() {
                
                // Si 'xhr.readyState == 4' Se ha recibido el cuerpo de la respuesta. Es el momento 
                // en que esta puede procesarse
                if (xhr2.readyState == 4) {
                    
                    // La funcion split() genera un Array a partir de una cadena de texto
                    // tomando como caracter separador el que le pasamos por parámetro,
                    // en este caso '-'
                    lista = xhr2.responseText.split("-");

                    // Enlazamos la variable listContainer con el <div> tablalibros
                    // para cargar los libros  cargados desde el servlet
                    var listContainer = document.getElementById("tablalibros");
                    listContainer.innerHTML = "";

                    var listElement = document.createElement("table");
                    listElement.id = "listalibros";

                    listContainer.appendChild(listElement);

                    var ColumnsHeader = document.createElement("th");
                    ColumnsHeader.innerHTML = "Titulo";
                    listElement.appendChild(ColumnsHeader);

                    // Set up a loop that goes through the items in listItems one at a time
                    var numberOfListItems = lista.length;
                    if (numberOfListItems < 0) {
                        document.getElementById("respuesta2").innerHTML = "Sin resultados ...";
                    } else {
                        for (var i = 0; i < numberOfListItems - 1; ++i) {

                            // create a <li> for each one.
                            var listItem = document.createElement("tr");

                            // add the item text
                            listItem.innerHTML = lista[i];

                            // add listItem to the listElement
                            listElement.appendChild(listItem);

                        }
                    }
                }


            }

            function GetEditorialAjaxConnect() {
                // solo para Explorer 6 y anteriores
                if (window.ActiveXObject)
                    xhr2 = new ActiveXObject("Microsoft.XMLHttp");
                // resto de navegadores
                else if ((window.XMLHttpRequest) || (typeof XMLHttpRequest) != undefined)
                    xhr2 = new XMLHttpRequest();
                // si el navegador no soporta AJAX
                else {
                    alert("Su navegador no soporta AJAX");
                    return;
                }

                GetEditorialEnviarPeticion();
            }

        </script>

    </head>
    <body onLoad="OnLoadAjaxConnect();">

        <h3>Selecciona la editorial:</h3>
        <div id="listaeditoriales"></div>

        <button type="button" onclick="GetEditorialAjaxConnect();">Aceptar</button>
        <br><br>
        <div id="tablalibros"></div>

    </body>
</html>
