function buscacep() {
    let cep = document.getElementById("cep").value;

    if (cep !== "") {
        let url = "https://brasilapi.com.br/api/cep/v1/" + cep;

        let req = new XMLHttpRequest();
        req.open("GET", url);
        req.send();

        req.onload = () => {
            if (req.status === 200) {
                let endereco = JSON.parse(req.response);

                document.getElementById("cidade").value = endereco.city;
                document.getElementById("estado").value = endereco.state;
                document.getElementById("bairro").value = endereco.neighborhood;
                document.getElementById("logradouro").value = endereco.street;
            } else if (req.status === 400) {
                alert("CEP inválido");
            } else {
                alert("Erro ao fazer a requisição");
            }
        };
    }
}

window.onload = () => {
    let txtcep = document.getElementById("cep");
    txtcep.addEventListener("blur", buscacep);
};
