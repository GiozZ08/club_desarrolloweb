document.addEventListener('DOMContentLoaded', () => {
  const userTableBody = document.querySelector('#userTable tbody');
  const userModal = new bootstrap.Modal('#userModal');
  const form = document.getElementById('userForm');
  const formMode = document.getElementById('formMode');
  const modalTitle = document.getElementById('userModalLabel');

  // Campos
  const docInput = document.getElementById('doc');
  const nameInput = document.getElementById('name');
  const usernameInput = document.getElementById('username');
  const roleSelect = document.getElementById('role');
  const membField = document.getElementById('membresiaField');
  const membInput = document.getElementById('membershipId');

  // Botón "Nuevo usuario"
  document.getElementById('btnAddUser').addEventListener('click', () => {
    form.reset();
    formMode.value = 'create';
    modalTitle.textContent = 'Nuevo usuario';
    membField.classList.add('d-none');
    userModal.show();
  });

  // Mostrar/ocultar campo membresía
  roleSelect.addEventListener('change', () => {
    if (roleSelect.value === 'SOCIO') {
      membField.classList.remove('d-none');
      membInput.required = true;
    } else {
      membField.classList.add('d-none');
      membInput.required = false;
      membInput.value = '';
    }
  });

  // Cargar lista inicial
  loadUsers();

  // Envío del formulario (crear o editar)
  form.addEventListener('submit', async e => {
    e.preventDefault();
    const payload = {
      document: docInput.value,
      name: nameInput.value,
      userName: usernameInput.value,
      userType: roleSelect.value,
      idMembresia: roleSelect.value === 'SOCIO' ? Number(membInput.value) : null
    };

    try {
      if (formMode.value === 'create') {
        await fetch('/api/person', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(payload)
        });
      } else {
        await fetch(`/api/person/${payload.document}`, {
          method: 'PUT',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(payload)
        });
      }
      userModal.hide();
      loadUsers();
    } catch (err) {
      alert('Error al guardar: ' + err.message);
    }
  });

  // Función para cargar usuarios y volcarlos en la tabla
  async function loadUsers() {
    userTableBody.innerHTML = '';
    try {
      const res = await fetch('/api/person');
      const users = await res.json();
      users.forEach(u => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
          <td>${u.document}</td>
          <td>${u.name}</td>
          <td>${u.userName}</td>
          <td>${u.userType}</td>
          <td>
            <button class="btn btn-sm btn-primary btn-edit" data-doc="${u.document}">✎</button>
            <button class="btn btn-sm btn-danger btn-del"  data-doc="${u.document}">🗑</button>
          </td>`;
        userTableBody.appendChild(tr);
      });

      // Asignar eventos a botones
      document.querySelectorAll('.btn-edit').forEach(btn =>
        btn.addEventListener('click', onEdit)
      );
      document.querySelectorAll('.btn-del').forEach(btn =>
        btn.addEventListener('click', onDelete)
      );

    } catch (err) {
      console.error('Error cargando usuarios:', err);
    }
  }

  // Editar usuario
  async function onEdit(e) {
    const doc = e.currentTarget.dataset.doc;
    try {
      const res = await fetch(`/api/person/${doc}`);
      const u = await res.json();
      docInput.value = u.document;
      nameInput.value = u.name;
      usernameInput.value = u.userName;
      roleSelect.value = u.userType;
      roleSelect.dispatchEvent(new Event('change'));
      if (u.userType === 'SOCIO') {
        membInput.value = u.idMembresia || '';
      }
      formMode.value = 'edit';
      modalTitle.textContent = 'Editar usuario';
      userModal.show();
    } catch (err) {
      alert('Error al obtener usuario: ' + err.message);
    }
  }

  // Eliminar usuario
  async function onDelete(e) {
    if (!confirm('¿Seguro que desea eliminar este usuario?')) return;
    const doc = e.currentTarget.dataset.doc;
    try {
      await fetch(`/api/person/${doc}`, { method: 'DELETE' });
      loadUsers();
    } catch (err) {
      alert('Error al eliminar: ' + err.message);
    }
  }
});