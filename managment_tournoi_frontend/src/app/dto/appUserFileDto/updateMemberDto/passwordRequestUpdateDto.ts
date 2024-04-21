export interface PasswordRequestUpdateDto {
    memberId: string;
    oldPassword: string;
    newPassword: string;
    confirmNewPassword: string;
}
