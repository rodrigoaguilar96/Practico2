document.querySelector(".cargarReporte").addEventListener("click", cargarReporte);

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
            if (datosalidas[iR].egresados== null) {
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