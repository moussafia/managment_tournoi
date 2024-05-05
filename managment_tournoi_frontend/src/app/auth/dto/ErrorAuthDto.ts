export interface ErrorAuthDto {
    status: number;
    error: string;
    timestamp: Date;
    message: string;
    path: string;
}