let fieldsWithErrors = [];

function resetState(){
    for (let i = 0; i < fieldsWithErrors.length; i++){
        fieldsWithErrors[i].style.display = "none";
    }
    fieldsWithErrors = [];
}
function fieldError(field, errorMessage){
    let parent = field.parentElement; //this gets the Input_Container_Inner
    let errorBox = parent.nextElementSibling;
    errorBox.style.display = "block";
    errorBox.innerHTML = errorMessage;

    fieldsWithErrors.push(errorBox);
}
function trimValue(field){
    field.value = field.value.trim();
}

function isBlank(value){
    return value.length === 0;
}

function validateLoginForm(frm){
    resetState();

    let result = true;

    let tbUsername = frm.elements["loginUsername"];
    let tbPassword = frm.elements["loginPassword"];

    trimValue(tbUsername);

    if (tbUsername.value.length === 0){
        fieldError(tbUsername, "Username may not be blank");
        result = false;
    }
    if (tbPassword.value.length === 0){
        fieldError(tbPassword, "Password may not be blank");
        result = false;
    }
    
    return result;
}

function validateCommentForm(frm){
    resetState();
    let result = true;

    let tbComment = frm.elements["cCommentText"];
    trimValue(tbComment);

    if (tbComment.value.length === 0){
        fieldError(tbComment, "Comment must not be blank");
        result = false;
    }
    if (tbComment.value.length >= 4096){
        fieldError(tbComment, "Comment cannot exceed 4096 characters")
        result = false;
    }
    return result;
}

function validateIssueReport(frm){
    resetState();

    let result = true;

    let tbTitle = frm.elements["newIssueTitle"];
    let cbCategory = frm.elements["newIssueCategory"];
    let tbDesc = frm.elements["newIssueDesc"];

    tbTitle.value = tbTitle.value.trim();
    tbDesc.value = tbDesc.value.trim();

    if (tbTitle.value.length === 0){
        fieldError(tbTitle, "Issue title may not be blank");
        result = false;
    }
    if (cbCategory.selectedIndex === 0){
        fieldError(cbCategory, "Please select a category for the issue");
        result = false;
    }
    if (tbDesc.value.length === 0){
        fieldError(tbDesc, "Description may not be blank");
        result = false;
    }else if (tbDesc.value.length > 4096){
        fieldError(tbDesc, "Description exceeds maximum allowed length of 4096 characters");
        result = false;
    }

    return result;
}

function validateKnowledgeBasePromotion(frm){
    resetState();

    let result = true;

    let tbTitle = frm.elements["kbArticleName"];
    let tbDesc = frm.elements["kbArticleDesc"];
    let tbResolution = frm.elements["kbArticleResolution"];

    tbTitle.value = tbTitle.value.trim();
    tbDesc.value = tbDesc.value.trim();
    tbResolution.value = tbResolution.value.trim();

    if (tbTitle.value.length === 0){
        fieldError(tbTitle, "Issue title may not be blank");
        result = false;
    }
    if (tbDesc.value.length === 0){
        fieldError(tbDesc, "Description may not be blank");
        result = false;
    }else if (tbDesc.value.length > 4096){
        fieldError(tbDesc, "Description exceeds maximum allowed length of 4096 characters");
        result = false;
    }
    if (tbResolution.value.length === 0){
        fieldError(tbResolution, "Resolution may not be blank");
        result = false;
    }else if (tbResolution.value.length > 4096){
        fieldError(tbResolution, "Resolution exceeds maximum allowed length of 4096 characters");
        result = false;
    }

    return result;
}

function validateUserUpdate(frm){
    resetState();

    let result = true;
    let tbFirstName = frm.elements["uFirstName"];
    let tbLastName = frm.elements["uLastName"];
    let tbEmail = frm.elements["uEmail"];
    let tbPhone = frm.elements["uPhone"];

    trimValue(tbFirstName);
    trimValue(tbLastName);
    trimValue(tbEmail);
    
    if (tbFirstName.value.length === 0){
        fieldError(tbFirstName, "First name may not be blank");
        result = false;
    }
    if (tbLastName.value.length === 0){
        fieldError(tbLastName, "Last name may not be blank");
        result = false;
    }
    if (tbEmail.value.length === 0){
        fieldError(tbEmail, "Email address must be specified");
        result = false;
    }else if (!validateEmail(tbEmail.value)){
        fieldError(tbEmail, "Email is formatted incorrectly");
        result = false;
    }
    if (tbPhone.value.length === 0){
        fieldError(tbPhone, "Phone number may not be blank");
        result = false;
    }
    return result;
}

function validateUserCreation(frm){
    resetState();

    let result = true;
    let cbUserType = frm.elements["uAcctType"]
    let tbFirstName = frm.elements["uFirstName"];
    let tbLastName = frm.elements["uLastName"];
    let tbEmail = frm.elements["uEmail"];
    let tbPhone = frm.elements["uPhone"];

    trimValue(tbFirstName);
    trimValue(tbLastName);
    trimValue(tbEmail);

    if (cbUserType.selectedIndex === 0){
        fieldError(cbUserType, "Please select a user type for this new user");
        result = false;
    }

    if (tbFirstName.value.length === 0){
        fieldError(tbFirstName, "First name may not be blank");
        result = false;
    }
    if (tbLastName.value.length === 0){
        fieldError(tbLastName, "Last name may not be blank");
        result = false;
    }
    if (tbEmail.value.length === 0){
        fieldError(tbEmail, "Email address must be specified");
        result = false;
    }else if (!validateEmail(tbEmail.value)){
        fieldError(tbEmail, "Email is formatted incorrectly");
        result = false;
    }
    if (tbPhone.value.length === 0){
        fieldError(tbPhone, "Phone number may not be blank");
        result = false;
    }
    return result;
}

function validateEmail(value){
    let emailRegex = /\S+@\S+\.\S+/
    return emailRegex.test(value);
}
function validateSearchForm(frm){
    let searchBox = frm.elements["searchQuery"];
    searchBox.value = searchBox.value.trim();

    return searchBox.value.length !== 0;
}
function updateFormAction(formName, action){
    let form = document.forms[formName];
    if (form != null){
        if (form.elements["action"]){
            form.elements["action"].value = action;
            return true;
        }
    }
    return false;
}
function updateFormAndSubmit(formName, action){
    if (updateFormAction(formName, action)){
        document.forms[formName].submit();
    }
}