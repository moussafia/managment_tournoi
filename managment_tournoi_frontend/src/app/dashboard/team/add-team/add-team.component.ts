import { Component } from '@angular/core';
import { MemberShowDto } from 'src/app/dto/appUserFileDto/getDto/memberShowDto';
import { ParticipantCreateDto } from 'src/app/dto/participantDto/participantCreateDto';
import { ParticipantService } from 'src/app/services/participant/participant.service';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-add-team',
  templateUrl: './add-team.component.html',
  styleUrls: ['./add-team.component.css']
})
export class AddTeamComponent {

  showResults: boolean = false;
  selectedUser: {id:string, name: string, url: string}[]= [];
  memberShowDto?: MemberShowDto[];
  createTeamDto?: ParticipantCreateDto;
  team?: string;
  numberOfParticipants?: number;
  logo?: File;
  participantId?: string[];
  showUrlPic: File | null = null
  constructor(private userService : UserService, private participantService: ParticipantService){}

  

  searchMemberByName(event: any) {
    this.showResults = true
    const keywordSearch = event.target.value;
      this.userService.searchMember(keywordSearch, 0, 2).subscribe({
        next: data => this.memberShowDto = data.content
      })
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
    console.log(id)
    const indexUser = this.selectedUser.findIndex(user => user.id === id);
    if(indexUser != -1){
        this.selectedUser.splice(indexUser, 1);
    }
  }

  onFileSelected(event: Event) {
    const inputElement = event.target as HTMLInputElement;
    if (inputElement.files && inputElement.files.length > 0) {
      this.logo = inputElement.files[0];
    }
  }

  onSubmit() {
    if (!this.team || !this.logo || !this.numberOfParticipants) 
      return;

    this.participantId = this.selectedUser.map(user => user.id);
    this.createTeamDto = {
      usersIds: this.participantId,
      team: this.team,
      logo: this.logo,
      numberOfParticipants: this.numberOfParticipants
    }
    this.participantService.createTeam(this.createTeamDto).subscribe({
      next: data => console.log(data)
    })
  }   
  }
      

