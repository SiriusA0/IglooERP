// Crear funcion fetch

//onclick='load_client('#clientContainer')';
function load_client(clientContainer) {
    var keys = Object.keys(window.localStorage);
    for (let i = 0; i < window.localStorage.length; i++) {
        var clientCard = document.createElement('div');
        clientCard.className = 'card mb-3';
        clientCard.id = 'tarjeta_' + i;

        var clientCardRow = document.createElement('div');
        clientCardRow.className = 'row no-gutters';

        var clientCardCol4 = document.createElement('div');
        clientCardCol4.className = 'col-md-4';

        var clientProfilePicture = document.createElement('img');
        clientProfilePicture.src = 'img/noProfile.png';
        clientProfilePicture.className = 'pt-2 pl-2';
        clientProfilePicture.height = 140;

        var clientCardCol8 = document.createElement('div');
        clientCardCol8.className = 'col-md-8';

        var clientCardBody = document.createElement('div');
        clientCardBody.className = 'card-body';

        var clientCardTitle = document.createElement('h5');
        var importedclient = JSON.parse(window.localStorage.getItem(keys[i]));

        clientCardTitle.innerHTML = importedclient.nombre;
        clientCardTitle.className = 'card-title';


        clientCardCol4.appendChild(clientProfilePicture);
        clientCardBody.appendChild(clientCardTitle);
        clientCardCol8.appendChild(clientCardBody);
        clientCardRow.appendChild(clientCardCol4);
        clientCardRow.appendChild(clientCardCol8);
        clientCard.appendChild(clientCardRow);

        document.querySelector(clientContainer).appendChild(clientCard);
    }
}

