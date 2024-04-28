import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ChangeEvent } from '@ckeditor/ckeditor5-angular';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import { catchError, map, of, startWith } from 'rxjs';
import { DataResponse, DataState } from 'src/app/dto/data.state.';
import { RulesCreateDto } from 'src/app/dto/ruleDto/rulesCreateDto';
import { RuleService } from 'src/app/services/rule/rule.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-rule',
  templateUrl: './add-rule.component.html',
  styleUrls: ['./add-rule.component.css']
})
export class AddRuleComponent {
  public Editor = ClassicEditor;
  textRule?: string;
  readonly dataState = DataState;
  ruleResponseDto?: DataResponse<string, any> ;
  @ViewChild('formAddRule') formAddRule?: NgForm;
  rulesCreateDto?: RulesCreateDto;


  constructor(private ruleService: RuleService){}

  public onReady(editor: any) {
    console.log("CKEditor5 Angular Component is ready to use!", editor);
  }

  public onChange({ editor }: ChangeEvent) {
    const data = editor.getData();
  }

  onSubmit(){
    if(!this.textRule) return;
    this.rulesCreateDto ={description: this.textRule};
    this.ruleService.createRule(this.rulesCreateDto).pipe(
      map(data=>{
        return ({dataState: this.dataState.LOADED, data: data || []}); 
      }),
      startWith({dataState: this.dataState.LOADING}),
      catchError(error => of({dataState: this.dataState.ERROR, error: error.error.message }))
    ).subscribe({
      next: data => {
        this.ruleResponseDto = data as DataResponse<string, any>
        if(data.dataState === this.dataState.LOADED){
          this.swalSucces('Rules are created with Succes');
          this.formAddRule?.resetForm();
        }else if(data.dataState === this.dataState.ERROR){
            this.swalError(this.ruleResponseDto.error);
        }

      }
    })
  }

  swalError(textError: string | undefined){
    Swal.fire({
      icon: "error",
      title: "Oops...",
      text: textError,
    });
  }


  swalSucces(message: string | undefined){
    Swal.fire({
      position : "center",
      icon :"success",
      title : message,
      showConfirmButton : false,
      timer : 3500 
    })
  }

}
