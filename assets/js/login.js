// Obtiene formulario y controles
const form = document.getElementById('loginForm');
const successAlert = document.getElementById('successAlert');

// Al enviar form
form.addEventListener('submit', function (e) {
  e.preventDefault();
  e.stopPropagation();

  // Validar cada campo
  if (!form.checkValidity()) {
    form.classList.add('was-validated');
    return;
  }

  // Si pasa validación, mostramos mensaje
  successAlert.classList.remove('d-none');

  // Aquí puedes invocar tu llamada AJAX / fetch al backend:
  // fetch('/api/login', {method: 'POST', body: JSON.stringify({...})})...
});