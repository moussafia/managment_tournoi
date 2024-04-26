import { Component } from '@angular/core';

@Component({
  selector: 'app-add-team',
  templateUrl: './add-team.component.html',
  styleUrls: ['./add-team.component.css']
})
export class AddTeamComponent {

  showResults: boolean = false;
  selectedUser: {id:string, name: string, url: string}[]= [];

  onSubmit() {
  throw new Error('Method not implemented.');
  }
  formAddTeam: any;

  searchMemberByName(event: any) {
    this.showResults = true
    const keywordSearch = event.target.value;
    console.log(event.target.value)
  }

  handleClickOutside(){
    this.showResults = false
  }

  selectParticipant(id: string, name: string, url: string){
    const existingid = this.selectedUser.find(user => user.id === id);
    if(!existingid){
      this.selectedUser?.push({id: id,name: name,url: url});
    }
    this.showResults = false;
  }

  deleteSelectedUser(id: string) {
    const indexUser = this.selectedUser.findIndex(user => user.id == id);
    if(indexUser != -1){
        this.selectedUser.slice(indexUser, 1);
    }
  }
      
}
