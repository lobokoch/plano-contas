/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:56:10.144
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.planocontas.entity.planoconta;

import br.com.kerubin.api.financeiro.planocontas.entity.planoconta.PlanoContaEntity;
import br.com.kerubin.api.financeiro.planocontas.entity.planoconta.PlanoContaLookupResult;
import br.com.kerubin.api.financeiro.planocontas.TipoPlanoContaFinanceiro;
import br.com.kerubin.api.financeiro.planocontas.TipoReceitaDespesa;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import javax.inject.Inject;
import org.springframework.boot.test.mock.mockito.MockBean;
import br.com.kerubin.api.messaging.core.DomainEntityEventsPublisher;
import static org.mockito.Mockito.doAnswer;
import br.com.kerubin.api.messaging.core.DomainEventEnvelope;
import static org.mockito.ArgumentMatchers.any;
import br.com.kerubin.api.messaging.core.DomainEvent;
import br.com.kerubin.api.financeiro.planocontas.FinanceiroPlanoContasConstants;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import br.com.kerubin.api.financeiro.planocontas.common.PageResult;
import java.util.Arrays;
import java.util.Collection;
import br.com.kerubin.api.financeiro.planocontas.entity.planoconta.PlanoContaAutoComplete;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.kerubin.api.financeiro.planocontas.FinanceiroPlanoContasBaseEntityTest;


@RunWith(SpringRunner.class)
public class PlanoContaServiceTest extends FinanceiroPlanoContasBaseEntityTest {
	
	private static final String[] IGNORED_FIELDS = { "id", "createdBy", "createdDate", "lastModifiedBy", "lastModifiedDate" };
	
	@TestConfiguration
	static class PlanoContaServiceTestConfig {
		
		@Bean
		public PlanoContaListFilterPredicate planoContaListFilterPredicate() {
			return new PlanoContaListFilterPredicateImpl();
		}
		
		@Bean
		public PlanoContaService planoContaService() {
			return new PlanoContaServiceImpl();
		}
		
		@Bean
		public PlanoContaDTOConverter planoContaDTOConverter() {
			return new PlanoContaDTOConverter();
		}
		
	}
	
	
	@Inject
	protected PlanoContaService planoContaService;
	
	@Inject
	protected PlanoContaDTOConverter planoContaDTOConverter;
	
	@Inject
	protected PlanoContaBaseRepository planoContaBaseRepository;
	
	@MockBean
	protected DomainEntityEventsPublisher publisher;
	
	// BEGIN CREATE TESTS
	
	@Test
	public void testCreateWithAllFields() throws Exception {
		PlanoConta planoConta = new PlanoConta();
		
		planoConta.setId(java.util.UUID.randomUUID());
		planoConta.setCodigo(generateRandomString(255));
		planoConta.setDescricao(generateRandomString(255));
		planoConta.setTipoFinanceiro(TipoPlanoContaFinanceiro.DESPESA);
		planoConta.setTipoReceitaDespesa(TipoReceitaDespesa.VARIAVEL);
		
		PlanoContaEntity planoContaEntityParam = newPlanoContaEntity();
		PlanoContaLookupResult planoContaPai = newPlanoContaLookupResult(planoContaEntityParam);
		planoConta.setPlanoContaPai(planoContaPai);
		
		planoConta.setAtivo(true);
		
		// BEGIN check event created.
		doAnswer(invocation -> {
			DomainEventEnvelope<DomainEvent> envelope = invocation.getArgument(0);
			
			assertThat(envelope).isNotNull();
			assertThat(envelope.getPayload()).isNotNull();
			
			PlanoContaEvent event = (PlanoContaEvent) envelope.getPayload();
			assertThat(event.getId()).isNotNull();
			assertThat(event.getCodigo()).isEqualTo(planoConta.getCodigo());
			assertThat(event.getDescricao()).isEqualTo(planoConta.getDescricao());
			assertThat(event.getTipoFinanceiro()).isEqualTo(planoConta.getTipoFinanceiro());
			assertThat(event.getTipoReceitaDespesa()).isEqualTo(planoConta.getTipoReceitaDespesa());
			
			if (planoConta.getPlanoContaPai() == null) {
				assertThat(event.getPlanoContaPai()).isNull();
			}
			else {
				assertThat(event.getPlanoContaPai()).isEqualTo(planoConta.getPlanoContaPai().getId());
			}
			
			assertThat(event.getAtivo()).isEqualTo(planoConta.getAtivo());
			
			assertThat(FinanceiroPlanoContasConstants.DOMAIN).isEqualTo(envelope.getDomain());
			assertThat(FinanceiroPlanoContasConstants.SERVICE).isEqualTo(envelope.getService());
			
			assertThat("planoContaCreated").isEqualTo(envelope.getPrimitive());
			assertThat("kerubin").isEqualTo(envelope.getTenant());
			assertThat("kerubin").isEqualTo(envelope.getUser());
			assertThat("kerubin").isEqualTo(envelope.getApplication());
			assertThat("entity.PlanoConta").isEqualTo(envelope.getKey());
			
			return null;
		}).when(publisher).publish(any());
		// END check event created.
		
		PlanoContaEntity planoContaEntity = planoContaService.create(planoContaDTOConverter.convertDtoToEntity(planoConta));
		em.flush();
		verify(publisher, times(1)).publish(any());
		PlanoConta actual = planoContaDTOConverter.convertEntityToDto(planoContaEntity);
		
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(planoConta, IGNORED_FIELDS);
		
		
		assertThat(actual.getPlanoContaPai().getId()).isNotNull();
		assertThat(actual.getPlanoContaPai()).isEqualToIgnoringGivenFields(planoConta.getPlanoContaPai(), IGNORED_FIELDS);
		
		
	}
	
	@Test
	public void testCreateWithOnlyRecairedFields() throws Exception {
		PlanoConta planoConta = new PlanoConta();
		
		planoConta.setId(java.util.UUID.randomUUID());
		planoConta.setCodigo(generateRandomString(255));
		planoConta.setDescricao(generateRandomString(255));
		planoConta.setTipoFinanceiro(TipoPlanoContaFinanceiro.DESPESA);
		planoConta.setAtivo(true);
		
		// BEGIN check event created.
		doAnswer(invocation -> {
			DomainEventEnvelope<DomainEvent> envelope = invocation.getArgument(0);
			
			assertThat(envelope).isNotNull();
			assertThat(envelope.getPayload()).isNotNull();
			
			PlanoContaEvent event = (PlanoContaEvent) envelope.getPayload();
			assertThat(event.getId()).isNotNull();
			assertThat(event.getCodigo()).isEqualTo(planoConta.getCodigo());
			assertThat(event.getDescricao()).isEqualTo(planoConta.getDescricao());
			assertThat(event.getTipoFinanceiro()).isEqualTo(planoConta.getTipoFinanceiro());
			assertThat(event.getTipoReceitaDespesa()).isEqualTo(planoConta.getTipoReceitaDespesa());
			
			if (planoConta.getPlanoContaPai() == null) {
				assertThat(event.getPlanoContaPai()).isNull();
			}
			else {
				assertThat(event.getPlanoContaPai()).isEqualTo(planoConta.getPlanoContaPai().getId());
			}
			
			assertThat(event.getAtivo()).isEqualTo(planoConta.getAtivo());
			
			assertThat(FinanceiroPlanoContasConstants.DOMAIN).isEqualTo(envelope.getDomain());
			assertThat(FinanceiroPlanoContasConstants.SERVICE).isEqualTo(envelope.getService());
			
			assertThat("planoContaCreated").isEqualTo(envelope.getPrimitive());
			assertThat("kerubin").isEqualTo(envelope.getTenant());
			assertThat("kerubin").isEqualTo(envelope.getUser());
			assertThat("kerubin").isEqualTo(envelope.getApplication());
			assertThat("entity.PlanoConta").isEqualTo(envelope.getKey());
			
			return null;
		}).when(publisher).publish(any());
		// END check event created.
		
		PlanoContaEntity planoContaEntity = planoContaService.create(planoContaDTOConverter.convertDtoToEntity(planoConta));
		em.flush();
		verify(publisher, times(1)).publish(any());
		PlanoConta actual = planoContaDTOConverter.convertEntityToDto(planoContaEntity);
		
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(planoConta, IGNORED_FIELDS);
		
		assertThat(actual.getPlanoContaPai()).isNull();
		
	}
	// END CREATE TESTS
	
	// BEGIN READ TESTS
	
	@Test
	public void testRead1() {
		PlanoContaEntity expectedPlanoContaEntity = newPlanoContaEntity();
		java.util.UUID id = expectedPlanoContaEntity.getId();
		PlanoConta expected = planoContaDTOConverter.convertEntityToDto(expectedPlanoContaEntity);
		PlanoContaEntity readPlanoContaEntity = planoContaService.read(id);
		PlanoConta actual = planoContaDTOConverter.convertEntityToDto(readPlanoContaEntity);
		
		assertThat(actual).isEqualToComparingFieldByField(expected);
		
	}
	// END READ TESTS
	
	// BEGIN UPDATE TESTS
	
	@Test
	public void testUpdateWithAllFields() throws Exception {
		PlanoContaEntity oldPlanoContaEntity = newPlanoContaEntity();
		java.util.UUID id = oldPlanoContaEntity.getId();
				
		PlanoConta planoConta = new PlanoConta();
		planoConta.setId(id);
		
		planoConta.setCodigo(generateRandomString(255));
		planoConta.setDescricao(generateRandomString(255));
		planoConta.setTipoFinanceiro(TipoPlanoContaFinanceiro.DESPESA);
		planoConta.setTipoReceitaDespesa(TipoReceitaDespesa.VARIAVEL);
		
		PlanoContaEntity planoContaEntityParam = newPlanoContaEntity();
		PlanoContaLookupResult planoContaPai = newPlanoContaLookupResult(planoContaEntityParam);
		planoConta.setPlanoContaPai(planoContaPai);
		
		planoConta.setAtivo(true);
		
		// BEGIN check event updated.
		doAnswer(invocation -> {
			DomainEventEnvelope<DomainEvent> envelope = invocation.getArgument(0);
			
			assertThat(envelope).isNotNull();
			assertThat(envelope.getPayload()).isNotNull();
			
			PlanoContaEvent event = (PlanoContaEvent) envelope.getPayload();
			assertThat(event.getId()).isEqualTo(planoConta.getId());
			assertThat(event.getCodigo()).isEqualTo(planoConta.getCodigo());
			assertThat(event.getDescricao()).isEqualTo(planoConta.getDescricao());
			assertThat(event.getTipoFinanceiro()).isEqualTo(planoConta.getTipoFinanceiro());
			assertThat(event.getTipoReceitaDespesa()).isEqualTo(planoConta.getTipoReceitaDespesa());
			
			if (planoConta.getPlanoContaPai() == null) {
				assertThat(event.getPlanoContaPai()).isNull();
			}
			else {
				assertThat(event.getPlanoContaPai()).isEqualTo(planoConta.getPlanoContaPai().getId());
			}
			
			assertThat(event.getAtivo()).isEqualTo(planoConta.getAtivo());
			
			assertThat(FinanceiroPlanoContasConstants.DOMAIN).isEqualTo(envelope.getDomain());
			assertThat(FinanceiroPlanoContasConstants.SERVICE).isEqualTo(envelope.getService());
			
			assertThat("planoContaUpdated").isEqualTo(envelope.getPrimitive());
			assertThat("kerubin").isEqualTo(envelope.getTenant());
			assertThat("kerubin").isEqualTo(envelope.getUser());
			assertThat("kerubin").isEqualTo(envelope.getApplication());
			assertThat("entity.PlanoConta").isEqualTo(envelope.getKey());
			
			return null;
		}).when(publisher).publish(any());
		// END check event updated.
		
		PlanoContaEntity planoContaEntity = planoContaService.update(id, planoContaDTOConverter.convertDtoToEntity(planoConta));
		em.flush();
		verify(publisher, times(1)).publish(any());
		
		PlanoConta actual = planoContaDTOConverter.convertEntityToDto(planoContaEntity);
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(planoConta, IGNORED_FIELDS);
		
		
		assertThat(actual.getPlanoContaPai().getId()).isNotNull();
		assertThat(actual.getPlanoContaPai()).isEqualToIgnoringGivenFields(planoConta.getPlanoContaPai(), IGNORED_FIELDS);
		
		
	}
	
	@Test
	public void testUpdateWithOnlyRecairedFields() throws Exception {
		PlanoContaEntity oldPlanoContaEntity = newPlanoContaEntity();
		java.util.UUID id = oldPlanoContaEntity.getId();
				
		PlanoConta planoConta = new PlanoConta();
		planoConta.setId(id);
		
		planoConta.setCodigo(generateRandomString(255));
		planoConta.setDescricao(generateRandomString(255));
		planoConta.setTipoFinanceiro(TipoPlanoContaFinanceiro.DESPESA);
		planoConta.setAtivo(true);
		
		// BEGIN check event updated.
		doAnswer(invocation -> {
			DomainEventEnvelope<DomainEvent> envelope = invocation.getArgument(0);
			
			assertThat(envelope).isNotNull();
			assertThat(envelope.getPayload()).isNotNull();
			
			PlanoContaEvent event = (PlanoContaEvent) envelope.getPayload();
			assertThat(event.getId()).isEqualTo(planoConta.getId());
			assertThat(event.getCodigo()).isEqualTo(planoConta.getCodigo());
			assertThat(event.getDescricao()).isEqualTo(planoConta.getDescricao());
			assertThat(event.getTipoFinanceiro()).isEqualTo(planoConta.getTipoFinanceiro());
			assertThat(event.getTipoReceitaDespesa()).isEqualTo(planoConta.getTipoReceitaDespesa());
			
			if (planoConta.getPlanoContaPai() == null) {
				assertThat(event.getPlanoContaPai()).isNull();
			}
			else {
				assertThat(event.getPlanoContaPai()).isEqualTo(planoConta.getPlanoContaPai().getId());
			}
			
			assertThat(event.getAtivo()).isEqualTo(planoConta.getAtivo());
			
			assertThat(FinanceiroPlanoContasConstants.DOMAIN).isEqualTo(envelope.getDomain());
			assertThat(FinanceiroPlanoContasConstants.SERVICE).isEqualTo(envelope.getService());
			
			assertThat("planoContaUpdated").isEqualTo(envelope.getPrimitive());
			assertThat("kerubin").isEqualTo(envelope.getTenant());
			assertThat("kerubin").isEqualTo(envelope.getUser());
			assertThat("kerubin").isEqualTo(envelope.getApplication());
			assertThat("entity.PlanoConta").isEqualTo(envelope.getKey());
			
			return null;
		}).when(publisher).publish(any());
		// END check event updated.
		
		PlanoContaEntity planoContaEntity = planoContaService.update(id, planoContaDTOConverter.convertDtoToEntity(planoConta));
		em.flush();
		verify(publisher, times(1)).publish(any());
		
		PlanoConta actual = planoContaDTOConverter.convertEntityToDto(planoContaEntity);
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(planoConta, IGNORED_FIELDS);
		
		assertThat(actual.getPlanoContaPai()).isNull();
		
	}
	// END UPDATE TESTS
	
	// BEGIN DELETE TESTS
	
	@Test
	public void testDelete1() {
		PlanoContaEntity expected = newPlanoContaEntity();
		java.util.UUID id = expected.getId();
		
		PlanoContaEntity planoConta = expected;
		
		expected = em.find(PlanoContaEntity.class, id);
		assertThat(expected).isNotNull();
		
		// BEGIN check event deleted.
		doAnswer(invocation -> {
			DomainEventEnvelope<DomainEvent> envelope = invocation.getArgument(0);
			
			assertThat(envelope).isNotNull();
			assertThat(envelope.getPayload()).isNotNull();
			
			PlanoContaEvent event = (PlanoContaEvent) envelope.getPayload();
			assertThat(event.getId()).isEqualTo(planoConta.getId());
			assertThat(event.getCodigo()).isEqualTo(planoConta.getCodigo());
			assertThat(event.getDescricao()).isEqualTo(planoConta.getDescricao());
			assertThat(event.getTipoFinanceiro()).isEqualTo(planoConta.getTipoFinanceiro());
			assertThat(event.getTipoReceitaDespesa()).isEqualTo(planoConta.getTipoReceitaDespesa());
			
			if (planoConta.getPlanoContaPai() == null) {
				assertThat(event.getPlanoContaPai()).isNull();
			}
			else {
				assertThat(event.getPlanoContaPai()).isEqualTo(planoConta.getPlanoContaPai().getId());
			}
			
			assertThat(event.getAtivo()).isEqualTo(planoConta.getAtivo());
			
			assertThat(FinanceiroPlanoContasConstants.DOMAIN).isEqualTo(envelope.getDomain());
			assertThat(FinanceiroPlanoContasConstants.SERVICE).isEqualTo(envelope.getService());
			
			assertThat("planoContaDeleted").isEqualTo(envelope.getPrimitive());
			assertThat("kerubin").isEqualTo(envelope.getTenant());
			assertThat("kerubin").isEqualTo(envelope.getUser());
			assertThat("kerubin").isEqualTo(envelope.getApplication());
			assertThat("entity.PlanoConta").isEqualTo(envelope.getKey());
			
			return null;
		}).when(publisher).publish(any());
		// END check event deleted.
		
		planoContaService.delete(id);
		verify(publisher, times(1)).publish(any());
		
		expected = em.find(PlanoContaEntity.class, id);
		assertThat(expected).isNull();
	}
	// END DELETE TESTS
	
	// BEGIN LIST TESTS
	
	@Test
	public void testList_FilteringByCodigo() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
		
		// Generate 33 records of data for PlanoContaEntity for this test.
		List<PlanoContaEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newPlanoContaEntity());
		}
		
		// Check if 33 records of PlanoContaEntity was generated.
		long count = planoContaBaseRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Creates a list filter for entity PlanoConta.
		PlanoContaListFilter listFilter = new PlanoContaListFilter();
		
		// Extracts 7 records of PlanoContaEntity randomly from testData.
		final int resultSize = 7;
		List<PlanoContaEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only PlanoContaEntity.codigo field and configure this list as a filter.
		List<String> codigoListFilter = filterTestData.stream().map(PlanoContaEntity::getCodigo).collect(Collectors.toList());
		listFilter.setCodigo(codigoListFilter);
		
		// Generates a pageable configuration, without sorting.
		int pageIndex = 0; // First page starts at index zero.
		int size = 33; // Max of 33 records per page.
		Pageable pageable = PageRequest.of(pageIndex, size);
		// Call service list method.
		Page<PlanoContaEntity> page = planoContaService.list(listFilter, pageable);
		
		// Converts found entities to DTOs and mount the result page.
		List<PlanoConta> content = page.getContent().stream().map(it -> planoContaDTOConverter.convertEntityToDto(it)).collect(Collectors.toList());
		PageResult<PlanoConta> pageResult = new PageResult<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
		
		// Asserts that result has size 7, in any order and has only rows with codigoListFilter elements based on codigo field.
		assertThat(pageResult.getContent())
		.hasSize(7)
		.extracting(PlanoConta::getCodigo)
		.containsExactlyInAnyOrderElementsOf(codigoListFilter);
		
		// Asserts some page result elements.
		assertThat(pageResult.getNumber()).isEqualTo(pageIndex);
		assertThat(pageResult.getNumberOfElements()).isEqualTo(7);
		assertThat(pageResult.getTotalElements()).isEqualTo(7);
		assertThat(pageResult.getTotalPages()).isEqualTo(1);
		
	}
	
	@Test
	public void testList_FilteringByCodigoWithoutResults() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for PlanoContaEntity for this test.
		List<PlanoContaEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newPlanoContaEntity());
		}
		
		// Check if 33 records of PlanoContaEntity was generated.
		long count = planoContaBaseRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Creates a list filter for entity PlanoConta.
		PlanoContaListFilter listFilter = new PlanoContaListFilter();
		
		// Generates a list with only PlanoContaEntity.codigo field with 1 not found data in the database and configure this list as a filter.
		List<String> codigoListFilter = Arrays.asList(generateRandomString(255));
		listFilter.setCodigo(codigoListFilter);
		
		// Generates a pageable configuration, without sorting.
		int pageIndex = 0; // First page starts at index zero.
		int size = 33; // Max of 33 records per page.
		Pageable pageable = PageRequest.of(pageIndex, size);
		// Call service list method.
		Page<PlanoContaEntity> page = planoContaService.list(listFilter, pageable);
		
		// Converts found entities to DTOs and mount the result page.
		List<PlanoConta> content = page.getContent().stream().map(it -> planoContaDTOConverter.convertEntityToDto(it)).collect(Collectors.toList());
		PageResult<PlanoConta> pageResult = new PageResult<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
		
		// Asserts that result has size 0 for unknown codigo field.
		assertThat(pageResult.getContent()).hasSize(0);
		
	}
	// END LIST TESTS
	
	// BEGIN Autocomplete TESTS
	@Test
	public void testAutoComplete() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for PlanoContaEntity for this test.
		List<PlanoContaEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newPlanoContaEntity());
		}
		
		// Check if 33 records of PlanoContaEntity was generated.
		long count = planoContaBaseRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of PlanoContaEntity randomly from testData.
		final int resultSize = 1;
		List<PlanoContaEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only PlanoContaEntity.codigo field and configure this list as a filter.
		List<String> codigoListFilter = filterTestData.stream().map(PlanoContaEntity::getCodigo).collect(Collectors.toList());
		// Mount the autocomplete query expression and call it.
		String query = codigoListFilter.get(0);
		Collection<PlanoContaAutoComplete> result = planoContaService.autoComplete(query);
		
		// Assert PlanoContaAutoComplete results.
		assertThat(result).isNotNull().hasSize(1)
		.extracting(PlanoContaAutoComplete::getCodigo)
		.containsExactlyInAnyOrderElementsOf(codigoListFilter);
	}
	
	// END Autocomplete TESTS
	
	// BEGIN ListFilter Autocomplete TESTS
	
	@Test
	public void testPlanoContaCodigoAutoComplete() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for PlanoContaEntity for this test.
		List<PlanoContaEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newPlanoContaEntity());
		}
		
		// Check if 33 records of PlanoContaEntity was generated.
		long count = planoContaBaseRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of PlanoContaEntity randomly from testData.
		final int resultSize = 1;
		List<PlanoContaEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only PlanoContaEntity.codigo field and configure this list as a filter.
		List<String> codigoListFilter = filterTestData.stream().map(PlanoContaEntity::getCodigo).collect(Collectors.toList());
		// Mount the autocomplete query expression and call it.
		String query = codigoListFilter.get(0);
		Collection<PlanoContaCodigoAutoComplete> result = planoContaService.planoContaCodigoAutoComplete(query);
		// Assert PlanoContaCodigoAutoComplete results.
		assertThat(result).isNotNull().hasSize(1)
		.extracting(PlanoContaCodigoAutoComplete::getCodigo)
		.containsExactlyInAnyOrderElementsOf(codigoListFilter);
	}
	
	
	@Test
	public void testPlanoContaDescricaoAutoComplete() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for PlanoContaEntity for this test.
		List<PlanoContaEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newPlanoContaEntity());
		}
		
		// Check if 33 records of PlanoContaEntity was generated.
		long count = planoContaBaseRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of PlanoContaEntity randomly from testData.
		final int resultSize = 1;
		List<PlanoContaEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only PlanoContaEntity.descricao field and configure this list as a filter.
		List<String> descricaoListFilter = filterTestData.stream().map(PlanoContaEntity::getDescricao).collect(Collectors.toList());
		// Mount the autocomplete query expression and call it.
		String query = descricaoListFilter.get(0);
		Collection<PlanoContaDescricaoAutoComplete> result = planoContaService.planoContaDescricaoAutoComplete(query);
		// Assert PlanoContaDescricaoAutoComplete results.
		assertThat(result).isNotNull().hasSize(1)
		.extracting(PlanoContaDescricaoAutoComplete::getDescricao)
		.containsExactlyInAnyOrderElementsOf(descricaoListFilter);
	}
	
	// END ListFilter Autocomplete TESTS
	
	// BEGIN Relationships Autocomplete TESTS
	
	@Test
	public void testPlanoContaPlanoContaPaiAutoComplete() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for PlanoContaEntity for this test.
		List<PlanoContaEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newPlanoContaEntity());
		}
		
		// Check if 33 records of PlanoContaEntity was generated.
		long count = planoContaBaseRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of PlanoContaEntity randomly from testData.
		final int resultSize = 1;
		List<PlanoContaEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only PlanoContaEntity.codigo field and configure this list as a filter.
		List<String> codigoListFilter = filterTestData.stream().map(PlanoContaEntity::getCodigo).collect(Collectors.toList());
		String query = codigoListFilter.get(0);
		
		Collection<PlanoContaAutoComplete> result = planoContaService.planoContaPlanoContaPaiAutoComplete(query);
		
		assertThat(result).isNotNull().hasSize(1)
		.extracting(PlanoContaAutoComplete::getCodigo)
		.containsExactlyInAnyOrderElementsOf(codigoListFilter);
	}
	
	// END Relationships Autocomplete TESTS
	
	// BEGIN tests for Sum Fields
	// END tests for Sum Fields
	
	// BEGIN tests for Sum Fields
	// END tests for Sum Fields
	
	// BEGIN TESTS DEPENDENCIES
	
	
	protected PlanoContaEntity newPlanoContaEntity() {
		PlanoContaEntity planoContaEntity = new PlanoContaEntity();
		
		planoContaEntity.setCodigo(generateRandomString(255));
		planoContaEntity.setDescricao(generateRandomString(255));
		planoContaEntity.setTipoFinanceiro(TipoPlanoContaFinanceiro.DESPESA);
		planoContaEntity.setTipoReceitaDespesa(TipoReceitaDespesa.VARIAVEL);
		planoContaEntity.setPlanoContaPai(null);
		planoContaEntity.setAtivo(true);
		
		planoContaEntity = em.persistAndFlush(planoContaEntity);
		return planoContaEntity;
	}
	
	
	protected PlanoContaLookupResult newPlanoContaLookupResult(PlanoContaEntity planoContaEntity) {
		PlanoContaLookupResult planoConta = new PlanoContaLookupResult();
		
		planoConta.setId(planoContaEntity.getId());
		planoConta.setCodigo(planoContaEntity.getCodigo());
		planoConta.setDescricao(planoContaEntity.getDescricao());
		
		return planoConta;
	}
	// END TESTS DEPENDENCIES

}
