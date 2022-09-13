/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.dsw.exemplos;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exemplo-json")
public class ExemploController {
    
    @GetMapping
    public String abrirJson() {
        return "{\n" +
"    profile: {\n" +
"        image: 'https://avatars.githubusercontent.com/u/37378515',\n" +
"        name: 'Daniel Izidio de Lima'\n" +
"    },\n" +
"    docInformation: [\n" +
"        {\n" +
"            title: 'E-mail',\n" +
"            value: 'daniel.izidioparacontato@gmail.com',\n" +
"            isLink: false\n" +
"        },\n" +
"        {\n" +
"            title: 'Telefone',\n" +
"            value: '(11) 99999-1234',\n" +
"            isLink: false\n" +
"        },\n" +
"        {\n" +
"            title: 'Data de nascimento',\n" +
"            value: '31/12/2000',\n" +
"            isLink: false\n" +
"        },\n" +
"        {\n" +
"            title: 'Phone',\n" +
"            value: '(11) 99999-1234',\n" +
"            isLink: false\n" +
"        },\n" +
"        {\n" +
"            title: 'Linkedin',\n" +
"            value: 'https://www.linkedin.com/in/daniel-izidio',\n" +
"            isLink: true\n" +
"        },\n" +
"        {\n" +
"            title: 'Github',\n" +
"            value: 'https://github.com/IZIDIOZZ',\n" +
"            isLink: true\n" +
"        },\n" +
"    ],\n" +
"    professionalExperience: {\n" +
"        title: 'Experiências profissionais',\n" +
"        experiences: [\n" +
"            {\n" +
"                company: 'Adega Alentejana',\n" +
"                role: 'Desenvolvedor Pleno',\n" +
"                workingTime: {\n" +
"                    title: 'Período: ',\n" +
"                    value: 'De 2020 até 2021'\n" +
"                }\n" +
"            },\n" +
"            {\n" +
"                company: 'Rock Content',\n" +
"                role: 'Desenvolvedor .Net Pleno',\n" +
"                workingTime: {\n" +
"                    title: 'Período: ',\n" +
"                    value: 'Desde dezembro 2021'\n" +
"                }\n" +
"            }\n" +
"        ]\n" +
"    },\n" +
"    AcademicEducation: {\n" +
"        title: 'Formação acadêmica',\n" +
"        educations: [\n" +
"            {\n" +
"                title: 'Técnico em Desenvolvimento de Sistemas',\n" +
"                educationalInstitution: 'Senai Ary Torres'\n" +
"            },\n" +
"            {\n" +
"                title: 'Tecnólogo em Analise e Desenvolvimento de Sistemas',\n" +
"                educationalInstitution: 'Centro Universitário Senac'\n" +
"            }\n" +
"        ]\n" +
"    },\n" +
"    knowledge: {\n" +
"        title: 'Conhecimentos',\n" +
"        knowledges: ['Java', 'Desenvolvimento web', 'C#', 'PHP', 'CSS', 'HTML', 'Javascript', 'Ruby']\n" +
"    },\n" +
"    languagesProficiency: {\n" +
"        title: 'Idiomas',\n" +
"        tableHeadersTitles: [\n" +
"            'Leitura',\n" +
"            'Escrita',\n" +
"            'Conversação'\n" +
"        ],\n" +
"        languagesProficiencies: [\n" +
"            {\n" +
"                language: 'Português',\n" +
"                proficiencyAt: {\n" +
"                    reading: 'Nativo',\n" +
"                    writing: 'Nativo',\n" +
"                    speaking: 'Nativo'\n" +
"                }\n" +
"            },\n" +
"            {\n" +
"                language: 'Ingles',\n" +
"                proficiencyAt: {\n" +
"                    reading: 'Avançado',\n" +
"                    writing: 'Bom',\n" +
"                    speaking: 'Bom'\n" +
"                }\n" +
"            },\n" +
"            {\n" +
"                language: 'Espanhol',\n" +
"                proficiencyAt: {\n" +
"                    reading: 'Básico',\n" +
"                    writing: 'Básico',\n" +
"                    speaking: 'Básico'\n" +
"                }\n" +
"            }\n" +
"        ]\n" +
"    }\n" +
"}";
    }
    
    @GetMapping("/v2")
    public String abrirJson2() {
        return "{\n" +
"    \"profile\": {\n" +
"        \"image\": \"https://avatars.githubusercontent.com/u/37378515\",\n" +
"        \"name\": \"Daniel Izidio de Lima\"\n" +
"    }\n" +
"}";
    }
    
}
