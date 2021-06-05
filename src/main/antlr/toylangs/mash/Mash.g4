grammar Mash;
@header { package toylangs.mash; }

// Seda reeglit pole vaja muuta
init: prog EOF;

// Programm koosneb paljudest konstruktsioonidest 
prog
    : (statement ';')+       // Tavalised laused mis peavad lõppema semikooloniga
    ;

statement
    : ifStatement
    | whileStatement
    | assignStatement
    | mushAddStatement
    | printStatement
    | variableDeclarationStatement
    | expression
    ;

ifStatement
    : 'if(' boolExpression ')' ifbody=statement
        ('elseif(' boolExpression ')' elseifbody=statement)*
        ('else' elsebody=statement)?
        'endif'
    ;

whileStatement
    : 'while(' boolExpression ')' whilebody=statement 'endif'
    ;

printStatement
    : 'print(' expression ')'
    ;

mushAddStatement
    : Identifier 'add' (intExpression|strExpression)
    ;

assignStatement
    : Identifier '=' expression;

variableDeclarationStatement
    : 'let' VariableName=Identifier ('=' expression)?
    ;

// Võib olla kas mush-, arv-, sõne- või tõeväärtus
expression
    : intExpression
    | strExpression
    | mushExpression
    | boolExpression
    ;


// ARVUD
intExpression
    : sumExpression ('>'|'<'|'=='|'<>') sumExpression
    | sumExpression
    ;

sumExpression
    : sumExpression ('+'|'-') termExpression
    | termExpression
    ;

termExpression
    : termExpression ('*'|'/') factorExpression
    | factorExpression
    ;

factorExpression
    : '-' factorExpression  # UnaryMinus
    | basicIntExpression    # SimpleCall
    ;

basicIntExpression
    : Integer
    | basicExpression
    ;

// SÕNED
strExpression
    : basicStrExpression ('=='|'<>') basicStrExpression
    | basicStrExpression
    ;

basicStrExpression
    : String
    | basicExpression
    ;

// MUSHID
mushExpression
    : mushSumExpression ('=='|'<>') mushSumExpression
    | mushSumExpression
    ;

mushSumExpression
    : mushSumExpression ('intersect'|'union'|'except'|'mash') basicMushExpression
    | basicMushExpression
    ;

basicMushExpression
    : Mush
    | basicExpression
    ;

// TÕEVÄÄRTUSED
boolExpression
    : boolExpression ('=='|'<>') boolSumExpression
    | boolSumExpression
    ;

boolSumExpression
    : boolSumExpression ('&'|'|') boolFactorExpression
    | boolFactorExpression
    ;

boolFactorExpression
    : '!' boolFactorExpression
    | basicBoolExpression
    ;

basicBoolExpression
    : Boolean
    | basicExpression
    ;

basicExpression
    : Identifier                # Variable
    | '(' basicExpression ')'   # Paren
    ;

Identifier : [a-zA-Z][a-zA-Z0-9]*;
Integer : ('0'|[1-9][0-9]*);
String : '"' ~["\n\r]* '"';
Boolean : 'true'|'false';
Mush :'['((Integer|(Integer(','Integer)*))|(String|(String(','String)*)))?']';

WS : [ \r\n\t]+ -> skip;