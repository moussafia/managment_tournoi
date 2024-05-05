import { createFeatureSelector, createSelector } from "@ngrx/store";
import { MeState } from "./me.reducer";


const getMeFeatureState = createFeatureSelector<MeState>('user');

export const getUserSucces = createSelector(
    getMeFeatureState,
    state =>{
        return state.user;
    } )


export const getUserFailure = createSelector(
    getMeFeatureState,
    state => state.error
)