document.addEventListener('DOMContentLoaded', () => {
  const modalEl = document.getElementById('membershipModal');
  const modal = new bootstrap.Modal(modalEl);
  const form = document.getElementById('membershipForm');
  const tableBody = document.getElementById('membershipTable');
  const titleEl = document.getElementById('membershipModalTitle');
  const saveBtn = document.getElementById('btnSaveMembership');

  // Cargar y renderizar lista de membresías
  async function loadMemberships() {
    tableBody.innerHTML = '<tr><td colspan="6">Cargando...</td></tr>';
    try {
      const res = await fetch('/api/membresias');
      const list = await res.json();
      tableBody.innerHTML = list.map(m => `
        <tr>
          <td>${m.idMembresia}</td>
          <td>${m.estado}</td>
          <td>${m.fondoActual.toFixed(2)}</td>
          <td>${m.limiteFondo.toFixed(2)}</td>
          <td>${m.tipoMembresia}</td>
          <td>
            <button class="btn btn-sm btn-primary btn-edit" data-id="${m.idMembresia}">✏</button>
            <button class="btn btn-sm btn-danger btn-delete" data-id="${m.idMembresia}">🗑</button>
          </td>
        </tr>
      `).join('');
    } catch (err) {
      tableBody.innerHTML = <tr><td colspan="6">Error cargando: ${err.message}</td></tr>;
    }
  }

  // Abrir modal para nueva membresía
  document.getElementById('btnNewMembership')
    .addEventListener('click', () => {
      titleEl.textContent = 'Nueva Membresía';
      form.reset();
      document.getElementById('membershipId').value = '';
      saveBtn.textContent = 'Crear';
      modal.show();
    });

  // Capturar clic en editar/eliminar
  tableBody.addEventListener('click', async e => {
    const id = e.target.dataset.id;
    if (!id) return;
    if (e.target.classList.contains('btn-edit')) {
      // editar
      titleEl.textContent = 'Editar Membresía';
      saveBtn.textContent = 'Actualizar';
      // cargar datos
      const res = await fetch(`/api/membresias/${id}`);
      const m = await res.json();
      document.getElementById('membershipId').value = m.idMembresia;
      document.getElementById('inputEstado').value = m.estado;
      document.getElementById('inputFondo').value = m.fondoActual;
      document.getElementById('inputLimite').value = m.limiteFondo;
      document.getElementById('inputTipo').value = m.tipoMembresia;
      modal.show();
    } else if (e.target.classList.contains('btn-delete')) {
      // eliminar
      if (!confirm('¿Seguro que desea eliminar esta membresía?')) return;
      await fetch(`/api/membresias/${id}`, { method: 'DELETE' });
      loadMemberships();
    }
  });

  // Enviar formulario (crear o actualizar)
  form.addEventListener('submit', async e => {
    e.preventDefault();
    const id = document.getElementById('membershipId').value;
    const dto = {
      idMembresia: id ? parseInt(id) : undefined,
      estado: document.getElementById('inputEstado').value.trim(),
      fondoActual: parseFloat(document.getElementById('inputFondo').value),
      limiteFondo: parseFloat(document.getElementById('inputLimite').value),
      tipoMembresia: document.getElementById('inputTipo').value.trim()
    };
    try {
      const method = id ? 'PUT' : 'POST';
      const url = id ? `/api/membresias/${id}` : '/api/membresias';
      const res = await fetch(url, {
        method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(dto)
      });
      if (!res.ok) throw new Error(await res.text());
      modal.hide();
      loadMemberships();
    } catch (err) {
      alert('Error al guardar: ' + err.message);
    }
  });

  // Al iniciar página
  loadMemberships();

  // Toggle sidebar
  document.getElementById('menu-toggle')
    .addEventListener('click', () => document.getElementById('wrapper').classList.toggle('toggled'));
  
  // Logout
  document.getElementById('btnLogout')
    .addEventListener('click', () => location.href = 'index.html');
});