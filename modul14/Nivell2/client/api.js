const URL = 'http://localhost:8080/v1/shops';

/** Retorna una llista de totes les botigues */
async function getShops() {
  return fetch(URL)
    .then(res => res.json())
    .catch(error => console.error('Error:', error));
}

/** Afegeix una nova botiga */
async function addShop(shop) {
  return fetch(URL, {
    method: 'POST',
    body: JSON.stringify(shop),
    headers: {
      'Content-Type': 'application/json'
    }
  }).then(res => res.json())
    .catch(error => console.error('Error:', error));
}

/** Afegeix un quadre a una botiga */
async function addPicture(shopId, picture) {
  return fetch(URL + '/' + shopId + '/pictures', {
    method: 'POST',
    body: JSON.stringify(picture),
    headers: {
      'Content-Type': 'application/json'
    }
  }).then(res => res.json())
    .catch(error => console.error('Error:', error));
}

/** Retorna una llista amb tots els quadres d'una botiga */
async function getShopPictures(shopId) {
  return fetch(URL + '/' + shopId + '/pictures')
    .then(res => res.json())
    .catch(error => console.error('Error:', error));
}

/** Elimina tots els quadres d'una botiga */
async function deleteShopPictures(shopId) {
  return fetch(URL + '/' + shopId + '/pictures', {
    method: 'DELETE'
  }).then(res => res.json())
    .catch(error => console.error('Error:', error));
}