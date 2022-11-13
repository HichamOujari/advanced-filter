import Head from 'next/head'
import xlsx from "json-as-xlsx"
import styles from '../styles/Home.module.css'
import { useState, useEffect } from 'react';
import axios from 'axios';
import moment from 'moment';

export default function Home() {

  const [listFields, setListFields] = useState([])
  const [fetchedUsers, setFetchedUsers] = useState([])

  const getData = async () => {
    let { data: diplomes } = await axios.get('http://localhost:8081/diplomes');
    let { data: formations } = await axios.get('http://localhost:8081/formations');
    let { data: grades } = await axios.get('http://localhost:8081/grades');
    let { data: fonctions } = await axios.get('http://localhost:8081/fonctions');

    diplomes = diplomes.map(diplome => diplome.name)
    formations = formations.map(formation => formation.name)
    grades = grades.map(grade => grade.name)
    fonctions = fonctions.map(fonction => fonction.name)

    setListFields({
      grade: {
        name: 'grade',
        operations: ['is equal to'],
        propositions: grades,
        isTaken: false
      },
      anciennete: {
        name: 'anciennete',
        operations: ['is equal to', 'more then', 'less then'],
        isTaken: false
      },
      age: {
        name: 'age',
        operations: ['is equal to', 'more then', 'less then'],
        isTaken: false
      },
      diplome: {
        name: 'diplome',
        operations: ['is equal to'],
        propositions: diplomes,
        isTaken: false
      },
      formation: {
        name: 'formation',
        operations: ['is equal to'],
        propositions: formations,
        isTaken: false
      },
      fonction: {
        name: 'fonction',
        operations: ['is equal to'],
        propositions: fonctions,
        isTaken: false
      },
    })
  }

  const fetchUser = async () => {
    const bodyFilter = {}
    for (const ele of Object.keys(listFields)) {
      if (listFields[ele].isTaken) {
        bodyFilter[ele] = {
          operation: document.getElementById("operation" + listFields[ele].name).value,
          value: document.getElementById("value" + listFields[ele].name).value
        }
      }
    }
    const { data: listUser } = await axios.post('http://localhost:8081/users/filter', bodyFilter);
    setFetchedUsers(listUser);
  }

  useEffect(() => {
    if (listFields.length == 0) getData();
    fetchUser();
  }, []);

  const download = () => {
    let data = [
      {
        sheet: "users",
        columns: [
          { label: "Prenom", value: "firstName" },
          { label: "Nom", value: "lastName" },
          { label: "Date de naissance", value: "dateNaissance" },
          { label: "Anciennete", value: "anciennete" },
          { label: "Grade", value: (row) => row.grade.name },
          { label: "Fonction", value: (row) => row.fonction.name },
          { label: "Grade", value: (row) => row.grade.name },
          {
            label: "formations", value: (row) => {
              const tmp = "";
              for (const ele of row.formations) {
                tmp += '- ' + ele.name + '\n'
              }
              return tmp;
            }
          },
          {
            label: "Diplomes", value: (row) => {
              const tmp = "";
              for (const ele of row.diplomes) {
                tmp += '- ' + ele.name + '\n'
              }
              return tmp;
            }
          }
        ],
        content: fetchedUsers,
      }
    ]

    let settings = {
      fileName: "ListUser", // Name of the resulting spreadsheet
      extraLength: 3, // A bigger number means that columns will be wider
    }

    xlsx(data, settings)
  }

  return listFields.length == 0 ? <>Loading</> : (
    <div className={styles.container}>
      <Head>
        <title>Generateur de requete</title>
        <meta name="description" content="Advanced Filter using React/SpringBoot" />
        <link rel="icon" href="/favicon.ico" />
      </Head>

      <main className={styles.main}>
        <p className={styles.title}>Generateur de requête</p>
        <div className={styles.conditions}>
          {Object.keys(listFields).map((ele, index) => {
            if (listFields[ele].isTaken) {
              return <div key={index} className={styles.condition}>
                <select onChange={(e) => {
                  let value = e.target.value;
                  let data = listFields;
                  data[ele].isTaken = false;
                  data[value].isTaken = true;
                  setListFields({
                    ...data
                  })
                }}>
                  <option value={listFields[ele].name} hidden={true} selected>{listFields[ele].name}</option>
                  {Object.keys(listFields).map((s_ele, jndex) => !listFields[s_ele].isTaken ? <option key={jndex} value={listFields[s_ele].name}>{listFields[s_ele].name}</option> : <></>)}
                </select>
                <select id={"operation" + listFields[ele].name}>
                  {listFields[ele].operations.map((sp_ele, pndex) => (<option key={pndex} value={sp_ele}>{sp_ele}</option>))}
                </select>
                {
                  listFields[ele].propositions ?
                    <select id={"value" + listFields[ele].name}>
                      {listFields[ele].propositions.map((sp_ele, pndex) => (<option key={pndex} value={sp_ele}>{sp_ele}</option>))}
                    </select> :
                    <input id={"value" + listFields[ele].name} type="text" placeholder="value" />
                }
                <p className={styles.removeClause} onClick={() => {
                  let data = listFields;
                  data[ele].isTaken = false;
                  setListFields({
                    ...data
                  });
                }}>x</p>
              </div>
            }
          })}
        </div>
        <div className={styles.buttons}>
          {true ? <button onClick={() => {
            let data = {};
            let isFounded = false;
            for (const ele of Object.keys(listFields)) {
              if (isFounded == false && listFields[ele].isTaken == false) {
                data[ele] = {
                  ...listFields[ele],
                  isTaken: true
                }
                isFounded = true
              }
              else data[ele] = listFields[ele];
            }
            setListFields({ ...data });
          }}>Add clause</button> : ''}
          <button onClick={fetchUser}>Filter</button>
        </div>
        <div className={styles.actionbtns}>
          <button onClick={() => download()} >Export (xlsx)</button>
        </div>
        <table>
          <thead>
            <tr>
              <th>Nom : </th>
              <th>Prenom : </th>
              <th>Grade : </th>
              <th>Fonction : </th>
              <th>Date de naissance : </th>
              <th>Anciennete : </th>
              <th>Formations : </th>
              <th>Diplomes : </th>
            </tr>
          </thead>

          <tbody>
            {fetchedUsers.map((user, index) => (
              <tr key={index}>
                <td>{user.lastName}</td>
                <td>{user.firstName}</td>
                <td>{user.grade.name}</td>
                <td>{user.fonction.name}</td>
                <td>{moment(user.dateNaissance).format('DD/MM/YYYY')}</td>
                <td>{user.anciennete}</td>
                <td>
                  <ul>
                    {user.formations.map((formation, jndex) => (<li key={jndex}>{formation.name}</li>))}
                  </ul>
                </td>
                <td>
                  <ul>
                    {user.diplomes.map((diplome, pndex) => (<li key={pndex}>{diplome.name}</li>))}
                  </ul>
                </td>
              </tr>))}
          </tbody>
        </table>
      </main>
    </div>
  )
}
