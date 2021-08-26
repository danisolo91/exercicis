const URL = "http://localhost:8080/v1";

/** Retorna totes les feines */
async function getJobs() {
  return fetch(URL + "/jobs").then(r => r.json());
}

/** Retorna un empleat segons l'ID */
async function getEmployee(id) {
  return fetch(URL + '/employees/' + id)
    .then(response => response.json())
    .catch(error => console.error('Error:', error));
}

/** Retorna tots els empleats. També filtra per feina en cas de rebre'n una per paràmetre. */
async function getEmployees(job = '') {
  let path = '/employees/';

  if (job.length > 0) path += ('?job=' + job);

  return fetch(URL + path)
    .then(response => response.json())
    .catch(error => console.error('Error:', error));
}

/** Fegeix un empleat i retorna la resposta del server*/
async function addEmployee(employee) {
  return fetch(URL + '/employees/', {
    method: 'POST',
    body: JSON.stringify(employee),
    headers: {
      'Content-Type': 'application/json'
    }
  }).then(res => res.json())
    .catch(error => console.error('Error:', error));
}

/** Actualitza les dades d'un empleat i retorna la resposta del server */
async function updateEmployee(employee) {
  return fetch(URL + '/employees/' + employee.id, {
    method: 'PUT',
    body: JSON.stringify(employee),
    headers: {
      'Content-Type': 'application/json'
    }
  }).then(res => res.json())
    .catch(error => console.error('Error:', error));
}

/** Elimina un empleat segons l'ID i retorna la resposta del server */
async function deleteEmployee(id) {
  return fetch(URL + '/employees/' + id, {
    method: 'DELETE'
  }).then(response => response.json())
    .catch(error => console.error('Error:', error));
}