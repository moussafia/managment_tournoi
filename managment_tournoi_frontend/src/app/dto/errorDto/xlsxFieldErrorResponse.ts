export interface XlsxFieldErrorResponse {
    message: string;
    validationFieldMaps: { [key: number]: string[] };
}
