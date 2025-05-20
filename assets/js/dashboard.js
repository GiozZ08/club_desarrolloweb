// Espera a que DOM esté listo
document.addEventListener('DOMContentLoaded', () => {
  const wrapper = document.getElementById('wrapper');
  const btnToggle = document.getElementById('menu-toggle');
  const btnLogout = document.getElementById('btnLogout');

  // Toggle del sidebar
  btnToggle.addEventListener('click', () => {
    wrapper.classList.toggle('toggled');
  });

  // Cerrar sesión (redirección a login)
  btnLogout.addEventListener('click', () => {
    window.location.href = 'index.html';
  });

  // Cargar datos de ejemplo (más adelante vendrán de tu API)
  loadStats();
  loadRecentActivity();
});

function loadStats() {
  // Estos valores luego vendrán de una petición fetch('/api/dashboard/stats')
  const stats = {
    totalPartners: 128,
    totalInvoices: 53,
    totalMemberships: 76
  };
  document.getElementById('totalPartners').innerText = stats.totalPartners;
  document.getElementById('totalInvoices').innerText = stats.totalInvoices;
  document.getElementById('totalMemberships').innerText = stats.totalMemberships;
}

function loadRecentActivity() {
  // Ejemplo estático; reemplazar con fetch('/api/dashboard/activity')
  const activity = [
    { date: '2025-05-10', user: 'juanperez', action: 'Registró un nuevo socio' },
    { date: '2025-05-09', user: 'admin', action: 'Eliminó una factura' },
    { date: '2025-05-08', user: 'maria', action: 'Asignó membresía' },
  ];
  const tbody = document.getElementById('recentActivity');
  activity.forEach(item => {
    const tr = document.createElement('tr');
    tr.innerHTML = `
      <td>${item.date}</td>
      <td>${item.user}</td>
      <td>${item.action}</td>
    `;
    tbody.appendChild(tr);
  });
}