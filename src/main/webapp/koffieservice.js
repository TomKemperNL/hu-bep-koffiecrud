export class KoffieService {
    MAX_KOFFIE_STERKTE = 10;

    getKoffieSoorten() {
        return fetch('http://localhost:8080/restservices/koffie')
            .then(r => {
                if(r.status === 200){
                    return r.json();
                }else{
                    return Promise.resolve([]);
                }
            }).catch(()=>{
                return Promise.resolve([])
            })
    }

    voegKoffieToe(koffie){
        return fetch('http://localhost:8080/restservices/koffie', {
            method: 'POST',
            body: JSON.stringify(koffie),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(r => {
            if(r.status < 200 || r.status > 300){
                return r.text().then(t => {throw t});
            }
        });
    }
}