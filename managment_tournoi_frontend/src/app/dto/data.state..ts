export enum DataState{
    LOADING, LOADED, ERROR
}
export interface DataResponse<T, V>{
dataState: DataState,
data?: T,
error?: V,
}