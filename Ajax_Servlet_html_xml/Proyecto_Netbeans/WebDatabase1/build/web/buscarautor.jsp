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
        <title>Seleccione Autor</title>
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
            
            function GetAutorAjaxConnect() {
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

                GetAutorEnviarPeticion();
            }


            function OnLoadEnviarPeticion() {
                // preparar la peticion
                // true --> peticion asincrona, false --> peticion sincrona
                xhr.open("GET", "ServletAutor?", true);

                // informar de la funcion que procesara la respuesta
                xhr.onreadystatechange = OnLoadProcesarRespuesta; // sin parentesis

                // enviar la peticion
                xhr.send(null);
            }

            function GetAutorEnviarPeticion() {
                
                // Seleccionar el div donde vamos a cargar la lista
                var e = document.getElementById("listahtml");
                
                //Obtenemos el String del autor seleccionado
                var strAutor = e.options[e.selectedIndex].text;

                // preparar la peticion
                // true --> peticion asincrona, false --> peticion sincrona
                xhr2.open("GET", "BuscarAutor?autor=" + strAutor, true);

                // informar de la funcion que procesara la respuesta
                xhr2.onreadystatechange = GetAutorProcesarRespuesta; // sin parentesis

                // enviar la peticion
                xhr2.send(null);
            }

            function OnLoadProcesarRespuesta() {
                
                // Si 'xhr.readyState == 4' Se ha recibido el cuerpo de la respuesta. Es el momento 
                // en que esta puede procesarse
                if (xhr.readyState == 4) {
                    
                    // La funcion split() genera un Array a partir de una cadena de texto
                    // tomando como caracter separador el que le pasamos por parámetro,
                    // en este caso '-'
                    lista = xhr.responseText.split("-");

                    // Enlazamos la variable listContainer con el <div> tablalibros
                    // para cargar los libros  cargados desde el servlet
                    var listContainer = document.getElementById("listautores");

                    // Make the list itself which is a <ul>
                    var listElement = document.createElement("select");
                    listElement.id = "listahtml";

                    // add it to the page
                    listContainer.appendChild(listElement);

                    // El siguiente bucle for va añadiendo opciones en el select 'listElement'
                    var numberOfListItems = lista.length;

                    for (var i = 0; i < numberOfListItems - 1; ++i) {

                        // create a <li> para cada uno.
                        var listItem = document.createElement("option");

                        // añadimos el item lista[i] 
                        listItem.innerHTML = lista[i];
                        listElement.appendChild(listItem);

                    }
                }

            }

            function GetAutorProcesarRespuesta() {
                
                // Si 'xhr.readyState == 4' Se ha recibido el cuerpo de la respuesta. Es el momento 
                // en que esta puede procesarse                
                if (xhr2.readyState == 4) {
                    
                    // La funcion split() genera un Array a partir de una cadena de texto
                    // tomando como caracter separador el que le pasamos por parámetro,
                    // en este caso '-'
                    lista = xhr2.responseText.split("-");

                    // var listContainer = document.createElement("div");
                    var listContainer = document.getElementById("tablalibros");
                    listContainer.innerHTML = "";

                    var listElement = document.createElement("table");
                    listElement.id = "listalibros";

                    listContainer.appendChild(listElement);

                    var ColumnsHeader = document.createElement("th");
                    ColumnsHeader.innerHTML = "Titulo:";
                    listElement.appendChild(ColumnsHeader);


                    var numberOfListItems = lista.length;

                    for (var i = 0; i < numberOfListItems - 1; ++i) {

                        // creamos un <li> por cada elemento.
                        var listItem = document.createElement("tr");

                        // añadimos el item lista[i]
                        listItem.innerHTML = lista[i];
                        listElement.appendChild(listItem);

                    }
                }

            }



        </script>

    </head>
    <body onLoad="OnLoadAjaxConnect();">

        <h3>Selecciona el autor:</h3>
        <div id="listautores"> </div>

        <button type="button" onclick="GetAutorAjaxConnect();">Aceptar</button>
        <br><br>
        <div id="tablalibros"></div>

    </body>
</html>
