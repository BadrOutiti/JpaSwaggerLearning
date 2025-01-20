package ch.ipt.JpaSwaggerLearning;

import ch.ipt.JpaSwaggerLearning.model.*;
import ch.ipt.JpaSwaggerLearning.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.*;

@SpringBootApplication
public class JpaSwaggerLearningApplication {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CardRepository cardRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaSwaggerLearningApplication.class, args);
	}

	@PostConstruct
	private void insertDummyData() {
		// Creating dummy users
		UserEntity user1 = new UserEntity();
		user1.setName("Alice");
		user1.setPassword("password123");

		UserEntity user2 = new UserEntity();
		user2.setName("Bob");
		user2.setPassword("securepass");

		UserEntity user3 = new UserEntity();
		user3.setName("Charlie");
		user3.setPassword("mypassword");

		userRepository.saveAll(Arrays.asList(user1, user2, user3));

		// Creating accounts and linking to users
		AccountEntity account1 = new AccountEntity();
		account1.setUser(user1);

		AccountEntity account2 = new AccountEntity();
		account2.setUser(user1);

		AccountEntity account3 = new AccountEntity();
		account3.setUser(user2);

		AccountEntity account4 = new AccountEntity();
		account4.setUser(user3);

		accountRepository.saveAll(Arrays.asList(account1, account2, account3, account4));

		// Creating cards and linking to accounts
		CardEntity card1 = new CardEntity();
		card1.setCardNumber("1234-5678-9012-3456");

		CardEntity card2 = new CardEntity();
		card2.setCardNumber("2345-6789-0123-4567");

		CardEntity card3 = new CardEntity();
		card3.setCardNumber("3456-7890-1234-5678");

		CardEntity card4 = new CardEntity();
		card4.setCardNumber("4567-8901-2345-6789");

		CardEntity card5 = new CardEntity();
		card5.setCardNumber("5678-9012-3456-7890");

		card1.setUUID(UUID.randomUUID().toString());
		card2.setUUID(UUID.randomUUID().toString());
		card3.setUUID(UUID.randomUUID().toString());
		card4.setUUID(UUID.randomUUID().toString());
		card5.setUUID(UUID.randomUUID().toString());

		cardRepository.saveAll(Arrays.asList(card1, card2, card3, card4, card5));

		// Assigning cards to accounts
		account1.setCards(Arrays.asList(card1, card2));
		account2.setCards(Arrays.asList(card3));
		account3.setCards(Arrays.asList(card4));
		account4.setCards(Arrays.asList(card5));

		accountRepository.saveAll(Arrays.asList(account1, account2, account3, account4));

		System.out.println("Dummy users, accounts, and cards have been inserted.");

		// Inserting 20 random transactions
		List<CardEntity> cards = cardRepository.findAll();
		List<TransactionEntity> transactions = new ArrayList<>();

		Random random = new Random();
		for (int i = 0; i < 20; i++) {
			TransactionEntity transaction = new TransactionEntity();
			transaction.setAmountInChf(random.nextInt(500) + 10); // Random amount between 10 and 500
			transaction.setDate(new Date());
			transaction.setCardEntity(cards.get(random.nextInt(cards.size()))); // Assign to a random card
			transactions.add(transaction);
		}

		transactionRepository.saveAll(transactions);
		System.out.println("20 dummy transactions have been inserted.");
	}
}
