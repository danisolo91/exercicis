document.addEventListener("DOMContentLoaded", function (event) {

  /** Elements de la vista de botigues */
  const shopCard = document.getElementById('shops');
  const shopForm = document.getElementById('shop-form');
  const shopNameInput = document.getElementById('shop-name');
  const shopCapInput = document.getElementById('shop-capacity');
  const shopRecords = document.getElementById('shop-records');

  /** Elements de la vista de quadres */
  const pictureCard = document.getElementById('pictures');
  const pictureHeader = document.getElementById('picture-header');
  const maxCapSpan = document.getElementById('max-cap');
  const pictureForm = document.getElementById('picture-form');
  const pictureNameInput = document.getElementById('picture-name');
  const picturePriceInput = document.getElementById('picture-price');
  const pictureAuthorInput = document.getElementById('picture-author');
  const pictureRecords = document.getElementById('picture-records');
  const goBackButton = document.getElementById('go-back');
  const deletePicturesButton = document.getElementById('delete-pictures');

  /** Variable global que guarda les dades de la botiga seleccionada */
  let selectedShop = {
    id: '',
    name: '',
    maxCapacity: '',
    pictures: []
  };

  /** Events */
  shopForm.addEventListener('submit', saveShop);
  pictureForm.addEventListener('submit', savePicture);
  goBackButton.addEventListener('click', viewShops);
  deletePicturesButton.addEventListener('click', destroyPictures);

  loadShopsTable();

  /** Afegeix una botiga nova */
  function saveShop(e) {
    e.preventDefault();
    const shop = {
      name: shopNameInput.value,
      maxCapacity: shopCapInput.value
    }
    addShop(shop).then(res => {
      shopNameInput.value = '';
      shopCapInput.value = '';
      showToast(res[0]);
      loadShopsTable();
    });
  }

  /** Afegeix un quadre a una botiga */
  function savePicture(e) {
    e.preventDefault();
    const picture = {
      name: pictureNameInput.value,
      price: isNaN(picturePriceInput.value) ? -1 : picturePriceInput.value,
      entryDate: new Date(),
      author: pictureAuthorInput.value
    }
    addPicture(selectedShop.id, picture).then(res => {
      pictureNameInput.value = '';
      picturePriceInput.value = '';
      pictureAuthorInput.value = '';
      showToast(res[0] ? res[0] : 'Error! alguna cosa ha anat malament.');
      loadPicturesTable(selectedShop.id);
    });
  }

  /** Elimina tots els quadres d'una botiga */
  function destroyPictures(e) {
    if (selectedShop.pictures.length > 0) {
      deleteShopPictures(selectedShop.id).then(res => {
        selectedShop.pictures = [];
        loadPicturesTable(selectedShop.id);
      });
    }
  }

  /** Mostra la vista amb les botigues */
  function viewShops(e) {
    showView('shops');
  }

  /** Mostra la vista amb els quadres d'una botiga */
  function viewPictures(e) {
    showView('pictures');
    selectedShop = {
      id: e.target.dataset.id,
      name: e.target.dataset.name,
      maxCapacity: e.target.dataset.maxcap,
      pictures: []
    }
    pictureHeader.textContent = selectedShop.name;
    maxCapSpan.textContent = selectedShop.maxCapacity;
    loadPicturesTable(selectedShop.id);
  }

  /** Mostra la vista rebuda per paràmetre */
  function showView(view) {
    switch (view) {
      case 'shops':
        shopCard.classList = 'card';
        pictureCard.classList = 'd-none';
        break;
      case 'pictures':
        shopCard.classList = 'd-none';
        pictureCard.classList = 'card';
        break;
      default:
        console.log('Error al canviar de vista');
    }
  }

  /** Carrega la taula de botigues */
  function loadShopsTable() {
    getShops().then(shops => {
      shopRecords.innerHTML = '';
      if (shops.length > 0) {
        shops.forEach(shop => {
          const tr = document.createElement('tr');

          // Recorrem els attributs d'un objecte shop
          Object.entries(shop).forEach(attr => {
            const td = document.createElement('td');
            td.textContent = attr[1];
            tr.appendChild(td);
          });

          const td = document.createElement('td');
          td.className = 'text-center';

          const viewPicturesBtn = document.createElement('button');
          viewPicturesBtn.classList = 'btn btn-outline-secondary btn-sm';
          viewPicturesBtn.dataset.id = shop.id;
          viewPicturesBtn.dataset.name = shop.name;
          viewPicturesBtn.dataset.maxcap = shop.maxCapacity;
          viewPicturesBtn.textContent = 'Veure quadres';
          viewPicturesBtn.addEventListener('click', viewPictures);

          td.appendChild(viewPicturesBtn);
          tr.appendChild(td);

          shopRecords.appendChild(tr);
        });
      } else {
        const tr = document.createElement('tr');
        const td = document.createElement('td');
        td.textContent = 'No hi ha cap botiga';
        td.colSpan = 4;
        tr.appendChild(td);
        shopRecords.appendChild(tr);
      }
    });
  }

  /** Carrega la taula de quadres d'una botiga */
  function loadPicturesTable(shopId) {
    getShopPictures(shopId).then(pictures => {
      pictureRecords.innerHTML = '';
      if (pictures.length > 0) {
        pictures.forEach(picture => {
          selectedShop.pictures = [...selectedShop.pictures, picture];
          const tr = document.createElement('tr');

          // Recorrem els attributs d'un objecte shop
          Object.entries(picture).forEach(attr => {
            if (attr[0] !== 'shop') {
              const td = document.createElement('td');
              td.textContent = attr[1];
              tr.appendChild(td);
            }
          });

          pictureRecords.appendChild(tr);
        });
      } else {
        const tr = document.createElement('tr');
        const td = document.createElement('td');
        td.textContent = 'No hi ha cap quadre';
        td.colSpan = 5;
        tr.appendChild(td);
        pictureRecords.appendChild(tr);
      }
    });
  }

  function showToast(message) {
    const toastElem = document.getElementById('liveToast');
    const toastBody = document.getElementById('toastBody');
    toastBody.textContent = message;
    toast = new bootstrap.Toast(toastElem);
    toast.show();
    setTimeout(function () { toast.hide(); }, 3000);
  }
});