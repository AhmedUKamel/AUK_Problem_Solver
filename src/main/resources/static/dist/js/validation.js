// ### Fields ### //
const usernameField = document.getElementById('usernameField');
const profileField = document.getElementById('profileField');
const emailField = document.getElementById('emailField');
const nameField = document.getElementById('nameField');
const passwordField = document.getElementById('passwordField');
const confirmPasswordField = document.getElementById('confirmPasswordField');
const phoneField = document.getElementById('phoneField');
const bioField = document.getElementById('bioField');
const titleField = document.getElementById('titleField');
const profileFileField = document.getElementById('profileFileField');
const profileImgField = document.getElementById('profileImgField');
const codeforcesField = document.getElementById('codeforcesField');
const loginPasswordField = document.getElementById('loginPasswordField');
const submitButton = document.getElementById('submitButton');

// ### RegExps ### //
const regexEmail = new RegExp('^[a-zA-Z0-9](.?[a-zA-Z0-9]){5,}@gmail.com$');
const regexName = new RegExp('^[a-zA-Z]{3,}(?: [a-zA-Z]+){0,3}$');
const regexTitle = new RegExp('^[a-zA-Z@-_()]{3,}(?: [a-zA-Z@-_()]+){0,3}$');
const regexPassword = new RegExp('^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d ]{8,}$');
const regexPhone = new RegExp('^01\\d{9}$');
const regexBio = new RegExp('^.{10,}$');
const regexCodeforces = new RegExp('^https://codeforces.com/profile/[a-zA-Z0-9_]+$');

// ### Messages ### //
const messageEmail = 'Invalid Gmail address, must match example@gmail.com';
const messageName = 'Invalid name, must match from 1 to 4 only characters names';
const messageTitle = 'Invalid title, must match from 1 to 4 characters and @-_()';
const messagePassword = 'Invalid password, must match characters, at least one uppercase letter, one lowercase letter and one number';
const messagePhone = 'Invalid phone number, must match 01XXXXXXXXX';
const messageBio = 'Invalid bio, minimum length is 10 characters';
const messageConfirm = 'Invalid confirm password, must match password';

// ### APIs ### //
const apiCodeforces = `https://codeforces.com/api/user.info?handles=`;
const apiImgbb = 'https://api.imgbb.com/1/upload';

// ### Keys ### //
const keyImgbb = '74c5978c07474cba5beef43c916bb222'

// ### Default Values ### //
const defaultProfile = 'https://i.ibb.co/rHhm45M/avatar.png';

// ### Functions ### //
function validateEmail() {
    if (emailField) {
        const nextElement = emailField.nextElementSibling;
        emailField.classList.remove('border-theme-6', 'border-theme-9');
        nextElement.textContent = '';
        if (regexEmail.test(emailField.value)) {
            emailField.classList.add('border-theme-9');
            submitButton.disabled = false;
        } else {
            emailField.classList.add('border-theme-6');
            nextElement.textContent = messageEmail;
            submitButton.disabled = true;
        }
    }

}

function validatePassword() {
    if (passwordField) {
        const nextElement = passwordField.nextElementSibling;
        passwordField.classList.remove('border-theme-6', 'border-theme-9');
        nextElement.textContent = '';
        if (regexPassword.test(passwordField.value)) {
            passwordField.classList.add('border-theme-9');
            submitButton.disabled = false;
        } else {
            passwordField.classList.add('border-theme-6');
            nextElement.textContent = messagePassword;
            submitButton.disabled = true;
        }
    }
}

function validateConfirmPassword() {
    if (confirmPasswordField) {
        const nextElement = confirmPasswordField.nextElementSibling;
        confirmPasswordField.classList.remove('border-theme-6', 'border-theme-9');
        nextElement.textContent = '';
        if (passwordField.value === confirmPasswordField.value) {
            confirmPasswordField.classList.add('border-theme-9');
            submitButton.disabled = false;
        } else {
            confirmPasswordField.classList.add('border-theme-6');
            nextElement.textContent = messageConfirm;
            submitButton.disabled = true;
        }
    }
}

function validateName() {
    if (nameField) {
        const nextElement = nameField.nextElementSibling;
        nameField.classList.remove('border-theme-6', 'border-theme-9');
        nextElement.textContent = '';
        if (regexName.test(nameField.value)) {
            nameField.classList.add('border-theme-9');
            submitButton.disabled = false;
        } else {
            nameField.classList.add('border-theme-6');
            nextElement.textContent = messageName;
            submitButton.disabled = true;
        }
    }
}

function validateTitle() {
    if (titleField) {
        const nextElement = titleField.nextElementSibling;
        titleField.classList.remove('border-theme-6', 'border-theme-9');
        nextElement.textContent = '';
        if (regexTitle.test(titleField.value)) {
            titleField.classList.add('border-theme-9');
            submitButton.disabled = false;
        } else {
            titleField.classList.add('border-theme-6');
            nextElement.textContent = messageTitle;
            submitButton.disabled = true;
        }
    }
}

function validatePhone() {
    if (phoneField) {
        const nextElement = phoneField.nextElementSibling;
        phoneField.classList.remove('border-theme-6', 'border-theme-9');
        nextElement.textContent = '';
        if (regexPhone.test(phoneField.value)) {
            phoneField.classList.add('border-theme-9');
            submitButton.disabled = false;
        } else {
            phoneField.classList.add('border-theme-6');
            nextElement.textContent = messagePhone;
            submitButton.disabled = true;
        }
    }
}

function validateBio() {
    if (bioField) {
        const nextElement = bioField.nextElementSibling;
        bioField.classList.remove('border-theme-6', 'border-theme-9');
        nextElement.textContent = '';
        if (regexBio.test(bioField.value)) {
            bioField.classList.add('border-theme-9');
            submitButton.disabled = false;
        } else {
            bioField.classList.add('border-theme-6');
            nextElement.textContent = messageBio;
            submitButton.disabled = true;
        }
    }
}

async function validateCodeforcesLink() {
    if (codeforcesField) {
        const nextElement = codeforcesField.nextElementSibling;
        usernameField.classList.remove('border-theme-6', 'border-theme-9', 'border-theme-12');
        codeforcesField.classList.remove('border-theme-6', 'border-theme-9', 'border-theme-12');
        nextElement.textContent = '';
        submitButton.disabled = true;
        onChangeInput(usernameField);
        profileField.value = defaultProfile;
        if (regexCodeforces.test(codeforcesField.value)) {
            usernameField.classList.remove('border-theme-6', 'border-theme-9', 'border-theme-12');
            codeforcesField.classList.remove('border-theme-6', 'border-theme-9', 'border-theme-12');
            codeforcesField.classList.add('border-theme-12');
            usernameField.classList.add('border-theme-12');
            const splits = codeforcesField.value.split('/');
            const handle = splits[splits.length - 1];
            const APIUrl = `${apiCodeforces}${handle}`;
            try {
                const response = await fetch(APIUrl);
                const json = await response.json();
                if (json.status === 'OK') {
                    const user = json.result[0];
                    usernameField.classList.remove('border-theme-6', 'border-theme-9', 'border-theme-12');
                    codeforcesField.classList.remove('border-theme-6', 'border-theme-9', 'border-theme-12');
                    codeforcesField.classList.add('border-theme-9');
                    usernameField.classList.add('border-theme-9');
                    nextElement.textContent = '';
                    usernameField.value = user.handle;
                    profileField.value = user.titlePhoto;
                    submitButton.disabled = false;
                } else {
                    usernameField.classList.remove('border-theme-6', 'border-theme-9', 'border-theme-12');
                    codeforcesField.classList.remove('border-theme-6', 'border-theme-9', 'border-theme-12');
                    usernameField.classList.add('border-theme-6');
                    codeforcesField.classList.add('border-theme-6');
                    usernameField.value = ''
                    nextElement.textContent = 'Invalid handle provided';
                }
            } catch (error) {
                usernameField.classList.remove('border-theme-6', 'border-theme-9', 'border-theme-12');
                codeforcesField.classList.remove('border-theme-6', 'border-theme-9', 'border-theme-12');
                usernameField.classList.add('border-theme-6');
                codeforcesField.classList.add('border-theme-6');
                usernameField.value = ''
                nextElement.textContent = 'Error while fetching user';
            }
        } else {
            usernameField.classList.remove('border-theme-6', 'border-theme-9', 'border-theme-12');
            codeforcesField.classList.remove('border-theme-6', 'border-theme-9', 'border-theme-12');
            usernameField.classList.add('border-theme-6');
            codeforcesField.classList.add('border-theme-6');
            usernameField.value = ''
            nextElement.textContent = 'Invalid codeforces profile link';
        }
    }
}

async function handleSelectProfileImage() {
    submitButton.disabled = true;
    const imageBlob = profileFileField.files[0];
    const imageUrl = await uploadImageToImgbb(imageBlob, usernameField.value);
    if (imageUrl) {
        profileField.value = imageUrl;
        profileImgField.src = imageUrl;
    }
    submitButton.disabled = false;
}

async function uploadImageToImgbb(imageFile, imageName) {
    const formData = new FormData();
    formData.append('key', keyImgbb);
    formData.append('image', imageFile, imageName);
    try {
        const response = await fetch(apiImgbb, {
            method: 'POST', body: formData
        });
        const data = await response.json();
        return data.data.url;
    } catch (error) {
        return null;
    }
}

function onChangeInput(inputElement) {
    inputElement.classList.remove('border-theme-6');
    const nextElement = inputElement.nextElementSibling;
    if (nextElement && nextElement.tagName === 'P') {
        nextElement.textContent = '';
    }
}

// ### Event Listeners ### //
if (loginPasswordField) loginPasswordField.addEventListener('input', () => onChangeInput(loginPasswordField));
if (emailField) emailField.addEventListener('input', validateEmail);
if (nameField) nameField.addEventListener('input', validateName);
if (titleField) titleField.addEventListener('input', validateTitle);
if (passwordField) passwordField.addEventListener('input', validatePassword);
if (confirmPasswordField) confirmPasswordField.addEventListener('input', validateConfirmPassword);
if (codeforcesField) codeforcesField.addEventListener('input', validateCodeforcesLink);
if (phoneField) phoneField.addEventListener('input', validatePhone);
if (bioField) bioField.addEventListener('input', validateBio);
if (profileFileField) profileFileField.addEventListener('change', handleSelectProfileImage);

// ### Add Error Fields ### //
const inputFields = document.querySelectorAll('input:not([hidden])');
inputFields.forEach(input => {
    if (!input.nextElementSibling || input.nextElementSibling.tagName.toLowerCase() !== 'p') {
        const newParagraph = document.createElement('p');
        newParagraph.textContent = '';
        newParagraph.classList.add('text-xs', 'text-gray-600', 'mt-2', 'text-theme-6');
        input.parentNode.insertBefore(newParagraph, input.nextElementSibling);
    }
});