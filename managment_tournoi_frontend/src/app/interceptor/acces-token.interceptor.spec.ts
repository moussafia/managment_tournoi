import { TestBed } from '@angular/core/testing';

import { AccesTokenInterceptor } from './acces-token.interceptor';

describe('AccesTokenInterceptor', () => {
  beforeEach(() => TestBed.configureTestingModule({
    providers: [
      AccesTokenInterceptor
      ]
  }));

  it('should be created', () => {
    const interceptor: AccesTokenInterceptor = TestBed.inject(AccesTokenInterceptor);
    expect(interceptor).toBeTruthy();
  });
});
