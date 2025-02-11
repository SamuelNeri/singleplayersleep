# SinglePlayerSleep

Plugin para Minecraft que permite que jogadores durmam individualmente, sem necessidade de todos os jogadores dormirem ao mesmo tempo.

## Funcionalidades

- Permite que um único jogador durma para passar a noite
- Opção para controlar o clima (limpar chuva/tempestade)
- Comando para recarregar configurações
- Mensagens personalizadas ao dormir

## Requisitos

- Servidor Spigot/Paper 1.21.4
- Java 21 ou superior
- Maven para compilação

## Instalação

1. Baixe o arquivo `.jar` da última versão
2. Coloque o arquivo na pasta `plugins` do seu servidor
3. Reinicie o servidor
4. O arquivo de configuração será gerado em `plugins/SinglePlayerSleep/config.yml`

## Compilação

Para compilar o plugin:

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/singleplayersleep.git

# Entre na pasta do projeto
cd singleplayersleep

# Compile com Maven
mvn clean package
```

O arquivo `.jar` será gerado na pasta `target/`.

## Configuração

Edite o arquivo `config.yml`:

```yaml
# Define se o plugin deve limpar o clima ruim ao dormir
clear-weather: true
```

## Comandos

- `/spsreload` - Recarrega as configurações do plugin
  - Permissão: `singleplayersleep.reload`

## Permissões

- `singleplayersleep.reload` - Permite usar o comando de recarregar configurações

## Suporte

Se encontrar algum problema ou tiver sugestões, por favor abra uma [Issue](https://github.com/SamuelNeri/singleplayersleep/issues).