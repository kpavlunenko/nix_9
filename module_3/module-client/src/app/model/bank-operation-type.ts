
export enum BankOperationType {
  INCOME_SALARY = 'INCOME_SALARY',
  INCOME_OTHER = 'INCOME_OTHER',
  INCOME_TRANSFER = 'INCOME_TRANSFER',

  OUTCOME_PAYMENT = 'OUTCOME_PAYMENT',
  OUTCOME_TRANSFER = 'OUTCOME_TRANSFER',
  OUTCOME_CREDIT = 'OUTCOME_CREDIT',
  OUTCOME_OTHER = 'OUTCOME_OTHER'

}

export const BankOperationTypeMapping = {
  [BankOperationType.INCOME_SALARY]: 'salary',
  [BankOperationType.INCOME_OTHER]: 'other income',
  [BankOperationType.INCOME_TRANSFER]: 'transfer to card',
  [BankOperationType.OUTCOME_PAYMENT]: 'payment',
  [BankOperationType.OUTCOME_TRANSFER]: 'transfer from card',
  [BankOperationType.OUTCOME_CREDIT]: 'loan repayment',
  [BankOperationType.OUTCOME_OTHER]: 'other expense'
};

export const IncomeBankOperationTypeMapping = {
  [BankOperationType.INCOME_SALARY]: 'salary',
  [BankOperationType.INCOME_OTHER]: 'other income'
};

export const OutcomeBankOperationTypeMapping = {

  [BankOperationType.OUTCOME_PAYMENT]: 'payment',
  [BankOperationType.OUTCOME_TRANSFER]: 'transfer from card',
  [BankOperationType.OUTCOME_CREDIT]: 'loan repayment',
  [BankOperationType.OUTCOME_OTHER]: 'other expense'
};
