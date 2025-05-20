// Datos de ejemplo
let invoices = [
  {
    id: 1,
    socioDocument: 12345678,
    fechaFactura: '2025-04-01',
    valorTotal: 150.75,
    metodoPago: 'Tarjeta',
    status: true
  },
  {
    id: 2,
    socioDocument: 87654321,
    fechaFactura: '2025-04-05',
    valorTotal: 200.00,
    metodoPago: 'Efectivo',
    status: false
  }
];

// Renderiza la tabla de facturas
function renderInvoices() {
  const tbody = document.querySelector('#invoicesTable tbody');
  tbody.innerHTML = '';

  invoices.forEach(inv => {
    const tr = document.createElement('tr');
    tr.innerHTML = `
      <td>${inv.id}</td>
      <td>${inv.socioDocument}</td>
      <td>${inv.fechaFactura}</td>
      <td>${inv.valorTotal.toFixed(2)}</td>
      <td>${inv.metodoPago}</td>
      <td>${inv.status ? 'Pagada' : 'Pendiente'}</td>
      <td>
        <button class="btn btn-sm btn-primary btn-edit" data-id="${inv.id}">✏</button>
        <button class="btn btn-sm btn-danger btn-delete" data-id="${inv.id}">🗑</button>
      </td>
    `;
    tbody.appendChild(tr);
  });

  // Asociar eventos
  document.querySelectorAll('.btn-delete').forEach(btn =>
    btn.addEventListener('click', onDeleteInvoice)
  );
  document.querySelectorAll('.btn-edit').forEach(btn =>
    btn.addEventListener('click', onEditInvoice)
  );
}

// Crear nueva factura (aquí podrías abrir un modal o redirigir)
function onNewInvoice() {
  alert('Aquí iría el formulario para crear una nueva factura.');
}

// Editar: ejemplo muy simple
function onEditInvoice(evt) {
  const id = Number(evt.currentTarget.dataset.id);
  const inv = invoices.find(i => i.id === id);
  if (!inv) return;
  const nuevoTotal = prompt('Modificar valor total:', inv.valorTotal);
  if (nuevoTotal !== null) {
    inv.valorTotal = parseFloat(nuevoTotal);
    renderInvoices();
  }
}

// Borrar factura
function onDeleteInvoice(evt) {
  const id = Number(evt.currentTarget.dataset.id);
  if (confirm(`¿Eliminar factura #${id}?`)) {
    invoices = invoices.filter(i => i.id !== id);
    renderInvoices();
  }
}

// Inicialización
document.addEventListener('DOMContentLoaded', () => {
  renderInvoices();
  document.getElementById('btnNewInvoice')
    .addEventListener('click', onNewInvoice);

  // Toggle sidebar
  document.getElementById('menu-toggle')
    .addEventListener('click', () => {
      document.getElementById('wrapper').classList.toggle('toggled');
    });

  // Logout simulado
  document.getElementById('btnLogout')
    .addEventListener('click', () => location.href = 'index.html');
});