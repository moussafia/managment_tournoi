export interface ErrorResponse {
    status: number;
    error: string;
    timestamp: Date;
    message: string;
    path: string;
}
