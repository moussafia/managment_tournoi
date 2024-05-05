import { MemberShowDto } from "src/app/dto/appUserFileDto/getDto/memberShowDto";
import { ErrorAuthDto } from "../dto/ErrorAuthDto";
import { createReducer, on } from "@ngrx/store";
import { MeApiAction } from "./action";


export interface MeState{
    user: MemberShowDto,
    error: ErrorAuthDto
}

const InitialMeState : MeState = {
    user: {} as MemberShowDto,
    error: {} as ErrorAuthDto
}

export const MeReducer = createReducer<MeState>(
    InitialMeState,
    on(MeApiAction.meUserSuccess,(state, action)=>{
        return {
            ...state,
            user:action.user,
            error: {} as ErrorAuthDto
        }
    }),
    on(MeApiAction.meUserFailure,(state, action)=>{
        return {
            ...state,
            user:{} as MemberShowDto,
            error: action.error
        }
    })
)