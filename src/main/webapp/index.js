import {KoffieService} from './koffieservice.js'

let koffieService = new KoffieService()


let ulletje = document.querySelector('#koffielijst');

function refresh() {
    koffieService.getKoffieSoorten().then(koffiesoorten => {
        ulletje.innerHTML = '';
        for (let kf of koffiesoorten) {
            ulletje.innerHTML += `<li>${kf.naam}</li>`
        }
    })
}

refresh();

let furmpie = document.querySelector('#koffieform')
let knuppie = document.querySelector('#opsturen')

knuppie.addEventListener('click', e => {
    e.preventDefault();
    let rauweData = new FormData(furmpie);

    let data = {
        naam: rauweData.get('naam'),
        prijs: rauweData.get('prijs'), //beide manieren werken
        sterkte: furmpie.sterkte.value,
        barcode: furmpie.barcode.value,
    }

    koffieService.voegKoffieToe(data).then(() => {
        refresh();
    }).catch(err => {
        alert(err);
    })
});



