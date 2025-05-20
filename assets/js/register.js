import { registerUser } from './services/api.js';

const formR = document.getElementById('registerForm');
const successAlertR = document.getElementById('successAlertR');
const errorAlertR   = document.getElementById('errorAlertR');

formR.addEventListener('submit', async (e) => {
  e.preventDefault();
  formR.classList.remove('was-validated');
  successAlertR.classList.add('d-none');
  if (errorAlertR) errorAlertR.classList.add('d-none');

  if (!formR.checkValidity()) {
    formR.classList.add('was-validated');
    return;
  }

  const userData = {
    username: formR.elements['username'].value.trim(),
    email:    formR.elements['email'].value.trim(),
    password: formR.elements['password'].value.trim()
  };

  try {
    const newUser = await registerUser(userData);
    successAlertR.textContent = `Usuario ${newUser.username} registrado con éxito.`;
    successAlertR.classList.remove('d-none');
    // opcional: limpiar formulario o redirigir
    formR.reset();
  } catch (err) {
    console.error(err);
    if (errorAlertR) {
      errorAlertR.textContent = err.message;
      errorAlertR.classList.remove('d-none');
    }
  }
});
