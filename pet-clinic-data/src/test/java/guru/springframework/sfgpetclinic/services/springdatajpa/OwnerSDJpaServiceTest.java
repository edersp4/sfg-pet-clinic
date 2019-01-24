package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    public static final String SMITH = "Smith";
    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    OwnerSDJpaService ownerSDJpaService;
    private Owner smith = Owner.builder().id(1L).lastName(SMITH).build();

    @BeforeEach
    void setUp() {

    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(any())).thenReturn(smith);
        Owner result = ownerSDJpaService.findByLastName(SMITH);
        assertEquals(SMITH, result.getLastName());
        verify(ownerRepository, atLeastOnce()).findByLastName(any());

    }

    @Test
    void findAll() {
        Set<Owner> listOwners = new HashSet<>();
        listOwners.add(Owner.builder().id(1L).lastName(SMITH).build());
        listOwners.add(Owner.builder().id(2L).lastName(SMITH).build());

        when(ownerRepository.findAll()).thenReturn(listOwners);

        Set<Owner> result = ownerSDJpaService.findAll();

        assertNotNull(listOwners);
        assertEquals(2, result.size());

    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(smith));

        Owner result = ownerSDJpaService.findById(1L);
        assertNotNull(result);

    }

    @Test
    void save() {
        when(ownerRepository.save(any())).thenReturn(smith);
        Owner result = ownerSDJpaService.save(smith);
        verify(ownerRepository).save(any());
        assertEquals(smith.getId(), result.getId());
    }

    @Test
    void delete() {
        ownerSDJpaService.delete(smith);
        verify(ownerRepository).delete(any());
    }

    @Test
    void deleteById() {
        ownerSDJpaService.deleteById(smith.getId());
        verify(ownerRepository).deleteById(anyLong());
    }
}