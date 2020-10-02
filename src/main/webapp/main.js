let tbodyEstudiantes = document.querySelector("#tbodyEstudiantes");
let botonBuscar = document.querySelector("#botonBuscar").addEventListener("click",realizarBusqueda);
function llenarTabla(tbody,json){
    vaciarTabla(tbody);
    for (let i = 0; i < json.length; i++) {
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
        case 1: listarEstudiantes(); break;
        case 2: listarEstudiantesByGenero(input); break;
        case 3: listarEstudiantesByLibreta(input); break;
        case 4: listarEstudiantesByCarreraAndCiudad(input); break;
    }
}
//2c Listar estudiantes
function listarEstudiantes(){
	url = "http://localhost:8080/Practico3/rest/estudiantes/listaEstudiantes";
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