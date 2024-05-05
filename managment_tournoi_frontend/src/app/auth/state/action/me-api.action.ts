import { createAction, props } from "@ngrx/store";
import { ErrorResponse } from "../../dto/ErrorResponse";
import { MemberShowDto } from "src/app/dto/appUserFileDto/getDto/memberShowDto";


export const meUserSuccess = createAction('[Me api] get user authenticated with success',
props<{user: MemberShowDto}>());

export const meUserFailure = createAction('[Me api] get user authenticated with failure',
props<{error: ErrorResponse}>());