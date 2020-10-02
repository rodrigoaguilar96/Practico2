document.querySelector(".cargarReporte").addEventListener("click", cargarReporte);
document.querySelector(".carrera").addEventListener("click", cargarCarrera);

function cargarReporte() {
    console.log("entre")
    url = "http://localhost:8080/Practico3/rest/reporte/reporteGraduados";
    fetch(url)
        .then(r => r.json())
        .then(jsonO => {
            mostrarReporte(jsonO);
        })

}

async function mostrarReporte(json) {
    console.log(json);
    let col = [];
    col.push("Año");
    col.push("Ingresos");
    col.push("Egresos");

    var divContainer = document.querySelector("#reporteCarreras");
    divContainer.innerHTML = "";


    for (let i = 0; i < json.length; i++) {
        var h2 = document.createElement("h2");
        h2.append(json[i].carrera.nombre)
        divContainer.appendChild(h2)
        let table = document.createElement("table");
        table.className = "table mt-3 table-striped"
        let tr = document.createElement("tr")
        let thead = document.createElement("thead")
        for (let index = 0; index < col.length; index++) {
            let th = document.createElement("th")
            th.append(col[index]);
            tr.appendChild(th);
            thead.appendChild(tr)
        }
        table.appendChild(thead)
        divContainer.appendChild(table);
        let datosalidas = json[i].datosReporteCarreras;
        for (let iR = 0; iR < datosalidas.length; iR++) {
            tr = document.createElement("tr");
            let tdins = document.createElement("td")
            let tdegr = document.createElement("td")
            let tdaño = document.createElement("td")
            tdaño.append(datosalidas[iR].año)
            if (datosalidas[iR].inscriptos == null) {
                tdins.append(0)
            } else {
                tdins.append(datosalidas[iR].inscriptos)
            }
            if (datosalidas[iR].egresados == null) {
                tdegr.append(0)
            } else {
                tdegr.append(datosalidas[iR].egresados)
            }

            tr.appendChild(tdaño)
            tr.appendChild(tdins)
            tr.appendChild(tdegr)
            table.appendChild(tr)
        }
    }
}
function cargarCarrera() {
    url = "http://localhost:8080/Practico3/rest/carreras/listaCarreras";
    fetch(url)
        .then(r => r.json())
        .then(jsonO => {
            mostrarCarrera(jsonO);
        })
}
async function mostrarCarrera(json) {
    console.log(json);
    let col = [];
    col.push("Id");
    col.push("Nombre");
    let tr = document.createElement("tr")
    let thead = document.createElement("thead")
    let table = document.createElement("table");
thead.className = "thead-dark"
    var divContainer = document.querySelector("#tablaCarreras");
    divContainer.innerHTML = "";
    for (let index = 0; index < col.length; index++) {
        let th = document.createElement("th")
        th.append(col[index]);
        tr.appendChild(th);
        thead.appendChild(tr)
    }
    table.appendChild(thead)
    for (let i = 0; i < json.length; i++) {
        table.className = "table mt-3 table-striped"
        let tr = document.createElement("tr")
        
        
        divContainer.appendChild(table);
        tr = document.createElement("tr");
            let id = document.createElement("td")
            let nombre = document.createElement("td")
            id.append(json[i].carrera.id)
            nombre.append(json[i].carrera.nombre)

            tr.appendChild(id)
            tr.appendChild(nombre)
            table.appendChild(tr)
    }
}


//lautaro
let tbodyEstudiantes = document.querySelector("#tbodyEstudiantes");
let botonBuscar = document.querySelector("#botonBuscar").addEventListener("click",realizarBusqueda);
function llenarTabla(tbody,json){
    
    vaciarTabla(tbody);
    console.log(keys(json).length);

    console.log(json.length);
    for (let i = 0; i < json.length; i++) {
        console.log("json adentro");
        let tr = document.createElement("TR");
        for (let key in json[i]) {
            let td = document.createElement("TD");
            let node = document.createTextNode(json[i][key]);
            td.appendChild(node);
            tr.appendChild(td);
        }
        tbody.appendChild(tr);
    }
}
function vaciarTabla(tbody){
    if(tbody.children.length > 0){
        tbody.innerHTML = "";
    }
}
function realizarBusqueda(){
    let input = document.querySelector("#inputCriterio").value;
    let select = document.querySelector("#selectCriterio").value;
    switch (select) {
        case '1': listarEstudiantes(); break;
        case '2': listarEstudiantesByGenero(input); break;
        case '3': listarEstudiantesByLibreta(input); break;
        case '4': listarEstudiantesByCarreraAndCiudad(input); break;
    }
}
//2c Listar estudiantes
function listarEstudiantes(){
    let url = "http://localhost:8080/Practico3/rest/estudiantes/listaEstudiantes";
    
	fetch(url)
	.then(r => r.json())
    .then(json => {
        llenarTabla(tbodyEstudiantes,json);
	})
}
//2d Listar estudiantes segun libreta
function listarEstudiantesByLibreta(id){
	url = "http://localhost:8080/Practico3/rest/estudiantes/estudianteById?libreta="+id;
	fetch(url)
	.then(r => r.json())
    .then(json => {
        llenarTabla(tbodyEstudiantes,json);
	})
}
//2e Listar estudiantes segun genero
function listarEstudiantesByGenero(genero){
	url = "http://localhost:8080/Practico3/rest/estudiantes/estudianteByGenero?genero="+genero;
	fetch(url)
	.then(r => r.json())
    .then(json => {
        llenarTabla(tbodyEstudiantes,json);
	})
}
//2g Listar estudiantes segun carrera y ciudad
function listarEstudiantesByCarreraAndCiudad(input){
    let split = input.split(",");
    let carrera = split[0];
    let ciudad = split[1];
	url = "http://localhost:8080/Practico3/rest/estudiantes/estudianteByCarreraAndCiudad";
	fetch(url)
	.then(r => r.json())
    .then(json => {
        llenarTabla(tbodyEstudiantes,json);
	})
}
//2a Dar de alta un estudiante
function altaEstudiante(){
    let estudiante = {
        
    };
    let url = "http://localhost:8080/Practico3/rest/estudiantes/addEstudiante";
    fetch(url, {
        method: 'POST',
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify(estudiante)
    });
}