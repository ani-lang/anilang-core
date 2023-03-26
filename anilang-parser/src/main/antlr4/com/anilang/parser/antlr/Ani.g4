grammar Ani;

file
    :   body EOF
    ;

body
    :   (importDeclaration NEWLINE)*
        bodyMember*
    ;

importDeclaration
    :   'import' StringLiteral
    ;

bodyMember
    :   scriptLine
    |   funcDeclaration
    |   classDeclaration
    |   structDeclaration
    |   NEWLINE+
    ;

structDeclaration
    :   'struct' Identifier structBody
    ;

structBody
    :   ':' NEWLINE structBodyMember* NEWLINE 'end'
    ;

type
    :   Identifier ('[' ']')*
    |   primitiveType ('[' ']')*
    ;

structBodyMember
    :   type Identifier (IntegerLiteral (',' IntegerLiteral)*)?
    |   NEWLINE
    ;

classDeclaration
    :   'class' Identifier classBody
    ;

classBody
    :   ':' NEWLINE classBodyMember* NEWLINE 'end'
    ;

classBodyMember
    :   variableDeclarator
    |   funcDeclaration
    |   structDeclaration
    |   sqlDeclarator
    |   NEWLINE
    ;

scriptLine
    :   variableDeclarator
    |   statement
    |   expression
    |   expression '(' expressionList? ')'
    |   sqlDeclarator
    |   NEWLINE+
    ;

sqlDeclarator
    :   inlineSqlClause Identifier sqlScriptBlock?
    ;

inlineSqlClause
    :   'sql.select'
    |   'sql.update'
    |   'sql.delete'
    |   'sql.insert'
    ;

sqlScriptBlock
    :   ':' NEWLINE sqlScriptBody NEWLINE 'end'
    ;

sqlScriptBody
    :   sqlSelectStatement
    ;

sqlSelectStatement
    :   sqlExpressionList NEWLINE
        sqlFromClause NEWLINE
        sqlWhereClause (NEWLINE sqlOrderByClause)?
    ;

sqlFromClause
    :   'from' Identifier (',' Identifier)*
    ;

sqlWhereClause
    :   'where' sqlExpression
    ;

sqlOrderByClause
    :   'order by' sqlExpressionList
    ;

sqlExpression
    :   primary #sqlPrimary
    |   '*' #sqlWildcard
    |   sqlExpression '.' (Identifier | '*') #sqlReference
    |   sqlExpression ('+'|'-') sqlExpression #sqlAdditionOperator
    |   sqlExpression ('*'|'/') sqlExpression #sqlMultiplyOperator
    |   sqlExpression ('=' | '!=') sqlExpression #sqlComparison
    |   sqlExpression ('<' '=' | '>' '=' | '<' | '>') sqlExpression #sqlOrderedComparison
    |   sqlExpression ('and' | 'or') sqlExpression #sqlLogicOperator
    |   '{' expression '}' #sqlContextInjection
    ;

sqlExpressionList
    :   sqlExpression (',' sqlExpression)*
    ;

funcDeclaration
    :   'def' Identifier formalParameters funcReturnTypeDeclaration? methodDeclarationRest
    ;

funcReturnTypeDeclaration
    :   '->' type
    ;

variableDeclarator
    :   variableDeclaratorId '=' variableInitializer
    ;

formalParameters
    :   '(' formalParameterDecls? ')'
    ;

formalParameterDecls
    :   type formalParameterDeclsRest
    ;

formalParameterDeclsRest
    :   variableDeclaratorId (',' formalParameterDecls)?
    ;

methodDeclarationRest
    :   methodBody
    ;

methodBody
    :   scriptBlock
    ;

scriptBlock
    :   ':' NEWLINE scriptLine* NEWLINE 'end'
    ;

statement
    :   'if' expression ifScriptBlock #ifStatement
    |   'while' expression scriptBlock #whileStatement
    |   'for' forControl scriptBlock #forStatement
    |   'match' expression matchScriptBlock #matchStatement
    |   'return' expression? #returnStatement
    |   'break' #breakStatement
    |   'continue' #continueStatement
    ;

matchScriptBlock
    :   ':' NEWLINE matchDeclaration* NEWLINE 'end'
    ;

matchDeclaration
    :   matchLabel
    |   NEWLINE
    ;

matchLabel
    :   'case' expression scriptBlock #expressionLabelCase
    |   'case' Identifier scriptBlock #identifierLabelCase
    |   'default' scriptBlock #defaultLabelCase
    ;

ifScriptBlock
    :   ':' NEWLINE ifMainScriptBlock elseScriptBlock* NEWLINE 'end'
    ;

ifMainScriptBlock
    :   scriptLine*
    ;

elseScriptBlock
    :   'else' NEWLINE scriptLine*
    ;

forControl
    :   itemForControl
    ;

itemForControl
    :   Identifier 'in' expression
    ;

variableDeclaratorId
    :   Identifier
    ;

variableInitializer
    :   expression
    |   arrayInitializer
    ;

arrayInitializer
    :   '[' (variableInitializer (',' variableInitializer)* (',')? )? ']'
    ;

expression
    :   primary #expressionValue
    |   expression '.' Identifier #expressionInstanceProperty
    |   expression ('+'|'-') expression #expressionAdditionOperator
    |   expression ('*'|'/') expression #expressionMultiplyOperation
    |   expression ('==' | '!=') expression #expressionEqualComparison
    |   expression ('<' '=' | '>' '=' | '<' | '>') expression #expressionOrderComparison
    |   expression 'and' expression #expressionLogicAnd
    |   expression 'or' expression #expressionLogicOr
    |   expression '(' expressionList? ')' #expressionMethodCall
    |   expression '=' expression #expressionAssignation
    |   'new' Identifier '(' expressionList? ')' #expressionInstantiation
    ;

primary
    :   literal
    |   Identifier
    |   '(' expression ')'
    ;

literal
    :   IntegerLiteral
    |   DecimalLiteral
    |   booleanLiteral
    |   StringLiteral
    |   'null'
    ;

booleanLiteral
    :   'true'
    |   'false'
    ;

expressionList
    :   expression (',' expression)*
    ;

primitiveType
    :   'boolean'
    |   'int'
    |   'float'
    |   'string'
    |   'list'
    |   'dict'
    |   'set'
    ;

Identifier
    :   [a-zA-Z_]+[a-zA-Z_0-9]*
    ;

IntegerLiteral
    :   ('0' | '1'..'9' '0'..'9'*)
    ;

DecimalLiteral
    :   ('0'..'9')+ '.' ('0'..'9')*
    ;

StringLiteral
    :   '\'' .*? '\''
    ;

// toss out whitespace
WS  :   [ \t]+ -> skip ;

COMMENT
    :   '/*' .*? '*/'    -> skip
    ;

LINE_COMMENT
    :   '#' ~[\r\n]* -> skip
    ;

NEWLINE
    : '\r'? '\n'
    ;
