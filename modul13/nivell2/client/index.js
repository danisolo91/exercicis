document.addEventListener("DOMContentLoaded", function (event) {

  /** Elements del formulari d'empleats */
  const addForm = document.getElementById('add');
  const idField = document.getElementById('id');
  const nameField = document.getElementById('name');
  const jobAddField = document.getElementById('job1');
  const addBtn = document.getElementById('addBtn');
  const addLegend = document.getElementById('addLegend');

  /** Elements del formulari de filtre */
  const filterForm = document.getElementById('filter');
  const jobFilterField = document.getElementById('job2');

  /** El cos de la taula de registres */
  const recordsTable = document.getElementById('records');

  /** Events */
  addForm.addEventListener('submit', saveEmployee);
  filterForm.addEventListener('submit', filterEmployees);

  /** Guardem les feines i definim un estat inicial del filtre */
  const jobs = getJobs();
  let filterState = '';

  loadJobFields();
  loadEmployeesTable(filterState);

  /** Crea o modifica un empleat */
  function saveEmployee(e) {
    e.preventDefault();

    const employee = {
      name: nameField.value,
      job: jobAddField.value
    }

    if (idField.value) {
      employee.id = idField.value;
      updateEmployee(employee).then(res => {
        console.log(res);
        loadEmployeesTable(filterState);
      });
    } else {
      addEmployee(employee).then(res => {
        console.log(res);
        loadEmployeesTable(filterState);
      });
    }

    restartAddForm();
  }

  /** Carrega les dades d'un empleat al formulari per modificar-lo */
  function updateEmployeeForm(e) {
    const id = e.target.dataset.id;
    getEmployee(id).then(data => {
      addLegend.textContent = 'Editar treballador';
      idField.value = data.id;
      nameField.value = data.name;
      jobAddField.value = data.job;
      addBtn.value = 'Modificar';
    });
  }

  /** Funció que s'executa al polsar el botó d'eliminar. Elimina un empleat de la BBDD. */
  function deleteEmployeeBtn(e) {
    const id = e.target.dataset.id
    deleteEmployee(id).then(data => {
      console.log(data);
      loadEmployeesTable(filterState);
    });
  }

  /** Carrega les feines als combobox */
  function loadJobFields() {
    jobs.then(data => {
      loadJobAddField(data);
      loadJobFilterField(data);
    });
  }

  /** Carrega les feines al combobox del formulari d'empleats */
  function loadJobAddField(data) {
    Object.entries(data).forEach(v => {
      const optionElem = document.createElement('option');
      optionElem.value = v[0];
      optionElem.textContent = v[1];
      jobAddField.appendChild(optionElem);
    });
  }

  /** Carrega les feines al combobox del filtre */
  function loadJobFilterField(data) {
    Object.entries(data).forEach(v => {
      const optionElem = document.createElement('option');
      optionElem.value = v[0];
      optionElem.textContent = v[1];
      jobFilterField.appendChild(optionElem);
    });
  }

  /** Posa l'estat per defecte del formulari d'empleats */
  function restartAddForm() {
    addLegend.textContent = 'Afegir un nou treballador';
    idField.value = '';
    nameField.value = '';
    addBtn.value = 'Afegir';
  }

  /** Funció que s'executa quan s'envía el formulari filtrar. 
   *  Mostra únicament els empleats d'una determinada feina.
  */
  function filterEmployees(e) {
    e.preventDefault();
    filterState = jobFilterField.value;
    loadEmployeesTable(filterState);
  }

  /** Actualitza les dades de la taula d'empleats, que poden
   *  estar filtrats per feina.
   */
  function loadEmployeesTable(job = '') {
    getEmployees(job).then(employees => {
      recordsTable.innerHTML = '';
      if (employees.length > 0) {
        employees.forEach(employee => {
          const tr = document.createElement('tr');

          // Recorrem els attributs d'un objecte empleat
          Object.entries(employee).forEach(attr => {
            const td = document.createElement('td');

            // Per l'atribut job mostrem el títol
            if (attr[0] === 'job') {
              jobs.then(job2 => {
                Object.entries(job2).forEach(attr2 => {
                  if (attr2[0] === attr[1]) td.textContent = attr2[1];
                });
              });
            } else {
              td.textContent = attr[1];
            }

            tr.appendChild(td);
          })

          const actionsTd = document.createElement('td');

          const updateBtn = document.createElement('button');
          updateBtn.innerHTML = 'Modificar';
          updateBtn.dataset.id = employee.id;
          updateBtn.addEventListener('click', updateEmployeeForm);
          actionsTd.appendChild(updateBtn);

          const deleteBtn = document.createElement('button');
          deleteBtn.innerHTML = 'Eliminar';
          deleteBtn.dataset.id = employee.id;
          deleteBtn.addEventListener('click', deleteEmployeeBtn);
          actionsTd.appendChild(deleteBtn);

          tr.appendChild(actionsTd);

          recordsTable.appendChild(tr);
        });
      } else {
        const span = document.createElement('span');
        span.textContent = 'No hi ha cap empleat';
        recordsTable.appendChild(span);
      }
    });
  }
});