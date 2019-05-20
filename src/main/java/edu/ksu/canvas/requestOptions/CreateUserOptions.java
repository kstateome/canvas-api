package edu.ksu.canvas.requestOptions;

public class CreateUserOptions extends BaseOptions {

    public CreateUserOptions termsOfUse(boolean accepted) {
        addSingleItem("user[terms_of_use]", Boolean.toString(accepted));
        return this;
    }

    public CreateUserOptions skipRegistration(boolean skipped) {
        addSingleItem("user[skip_registration]", Boolean.toString(skipped));
        return this;
    }

    public CreateUserOptions sendConfirmation(boolean send) {
        addSingleItem("pseudonym[send_confirmation]", Boolean.toString(send));
        return this;
    }

    public CreateUserOptions forceSelfRegistration(boolean force) {
        addSingleItem("pseudonym[force_self_registration]", Boolean.toString(force));
        return this;
    }

    public CreateUserOptions confirmationUrl(boolean confirmation) {
        addSingleItem("communication_channel[confirmation_url]", Boolean.toString(confirmation));
        return this;
    }

    public CreateUserOptions skipConfirmation(boolean skip) {
        addSingleItem("communication_channel[skip_confirmation]", Boolean.toString(skip));
        return this;
    }

    public CreateUserOptions forceValidations(boolean force) {
        addSingleItem("force_validations", Boolean.toString(force));
        return this;
    }

    public CreateUserOptions enableSisReactivation(boolean enable) {
        addSingleItem("enable_sis_reactivation", Boolean .toString(enable));
        return this;
    }

    public CreateUserOptions destination(String url) {
        addSingleItem("destination", url);
        return this;
    }
}
